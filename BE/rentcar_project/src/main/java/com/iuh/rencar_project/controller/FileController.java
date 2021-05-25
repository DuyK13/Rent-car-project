package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.service.template.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/23/2021 4:16 PM
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload/{id}")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile, @PathVariable long id) {
        String message = fileService.uploadFile(multipartFile, id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PutMapping(value = "/upload/cars")
    public ResponseEntity<?> uploadCarImage(@RequestPart(value = "file") MultipartFile multipartFile,@RequestPart(name = "car") CarRequest carRequest) {
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(carRequest.getName());
        return new ResponseEntity<>(new MessageResponse("test"), HttpStatus.OK);
    }

    @PutMapping("/upload/test")
    public ResponseEntity<?> uploadTest(@RequestPart(value = "file") MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(carRequest.getName());
        return new ResponseEntity<>(new MessageResponse("test"), HttpStatus.OK);
    }

}
