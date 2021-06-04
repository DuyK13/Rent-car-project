package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.entity.Post;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/23/2021 4:18 PM
 */
public interface IFileService {

    String uploadCarImage(MultipartFile multipartFile, String carName);

    String updateCarImage(MultipartFile multipartFile, Car car);

    String uploadPostImage(MultipartFile multipartFile, String title);

    String updatePostImage(MultipartFile multipartFile, Post currentPost);

    String uploadFileS3(MultipartFile multipartFile);

    String uploadMultiFileS3(MultipartFile[] multipartFiles);
}
