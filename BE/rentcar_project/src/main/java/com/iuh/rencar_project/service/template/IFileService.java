package com.iuh.rencar_project.service.template;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.iuh.rencar_project.entity.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/23/2021 4:18 PM
 */
public interface IFileService {
    // Upload and download to s3
    String uploadFile(MultipartFile multipartFile, Long id);

    String uploadCarImage(MultipartFile multipartFile, String carName);

    String updateCarImage(MultipartFile multipartFile, Car car);
}
