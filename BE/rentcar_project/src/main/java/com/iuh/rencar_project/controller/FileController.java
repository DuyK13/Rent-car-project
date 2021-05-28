package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.service.template.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/28/2021 2:34 PM
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/single-upload")
    public ResponseEntity<?> uploadFileInContent(@RequestPart(name = "file") MultipartFile multipartFile) {
        return new ResponseEntity<>(new MessageResponse(fileService.uploadFileS3(multipartFile)), HttpStatus.OK);
    }

    @PostMapping("/multiple-upload")
    public ResponseEntity<?> uploadMultipleFileInContent(@RequestPart(name = "files") MultipartFile[] multipartFiles) {
        return new ResponseEntity<>(new MessageResponse(fileService.uploadMultiFileS3(multipartFiles)), HttpStatus.OK);
    }
}
