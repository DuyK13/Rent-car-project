package com.iuh.rencar_project.utils.exception.bind;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/27/2021 12:15 PM
 */
public class FileUploadException extends RuntimeException{

    public FileUploadException(String message){
        super(message);
    }

    public FileUploadException(String message, Throwable e){
        super(message, e);
    }
}
