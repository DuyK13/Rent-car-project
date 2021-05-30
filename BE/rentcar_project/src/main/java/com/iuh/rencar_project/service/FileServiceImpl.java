package com.iuh.rencar_project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.service.template.IFileService;
import com.iuh.rencar_project.utils.StringUtils;
import com.iuh.rencar_project.utils.exception.bind.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/23/2021 4:19 PM
 */
@Service
public class FileServiceImpl implements IFileService {

    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.carFolder}")
    private String carFolder;

    @Value("${aws.s3.postFolder}")
    private String postFolder;

    @Value("${aws.s3.contentFolder}")
    private String contentFolder;

    @Autowired
    public FileServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadCarImage(MultipartFile multipartFile, String carName) {
        return this.uploadFile(multipartFile, carFolder, carName);
    }

    @Override
    public String updateCarImage(MultipartFile multipartFile, Car car) {
        String oldFileName = car.getImage().split("/")[5];
        this.removeFileFromS3Bucket(bucketName, carFolder, oldFileName);
        String carName = car.getName();
        return this.uploadFile(multipartFile, carFolder, carName);
    }

    @Override
    public String uploadPostImage(MultipartFile multipartFile, String title) {
        return this.uploadFile(multipartFile, postFolder, title);
    }

    @Override
    public String updatePostImage(MultipartFile multipartFile, Post currentPost) {
        String oldFileName = currentPost.getImage().split("/")[5];
        this.removeFileFromS3Bucket(bucketName, postFolder, oldFileName);
        String title = currentPost.getTitle();
        return this.uploadFile(multipartFile, postFolder, title);
    }

    @Override
    public String uploadFileS3(MultipartFile multipartFile) {
        this.uploadFile(multipartFile, contentFolder, multipartFile.getOriginalFilename());
        return "Upload file to content folder success";
    }

    @Override
    public String uploadMultiFileS3(MultipartFile[] multipartFiles) {
        for (MultipartFile file : multipartFiles) {
            this.uploadFile(file, contentFolder, file.getOriginalFilename());
        }
        return "Upload file to content folder success";
    }

    private String uploadFile(MultipartFile multipartFile, String folderName, String name) {
        String fileName = new SimpleDateFormat("ddMMyyyyHHmm").format(new Date());
        fileName += "-" + StringUtils.unAccent(name);
        try {
            File file = this.convertMultiPartFileToFile(multipartFile);
            fileName += "-" + file.getName();
            this.uploadFileToS3Bucket(bucketName, folderName, fileName, file);
            file.delete();
        } catch (Exception ex) {
            logger.error("File upload exception: ", ex);
            throw new FileUploadException("Upload file fail");
        }
        AmazonS3Client s3Client = (AmazonS3Client) amazonS3;
        return s3Client.getResourceUrl(bucketName, folderName + "/" + fileName);
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException ex) {
            logger.error("IO Error: ", ex);
        }
        return file;
    }

    @Async
    void uploadFileToS3Bucket(String bucketName, String folderName, String fileName, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + "/" + fileName, file).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
    }

    @Async
    void removeFileFromS3Bucket(String bucketName, String folderName, String fileName) {
        AmazonS3Client s3Client = (AmazonS3Client) amazonS3;
        s3Client.deleteObject(bucketName, folderName + "/" + fileName);
    }
}
