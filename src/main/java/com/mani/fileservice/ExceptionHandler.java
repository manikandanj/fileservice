/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice;

import com.mani.fileservice.exception.FileServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

/**
 *
 * @author mani
 */
@ControllerAdvice
@RestController
public class ExceptionHandler {
    
    @Value("${spring.http.multipart.max-file-size}")
    String maxFileSize;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = FileServiceException.class)
    public String fileServExceptionHandler(FileServiceException e) {
        return "Exception occured :" + e.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MultipartException.class)
    public String MultipartExceptionHandler(MultipartException e) {
        return "File exceeded maximum size of " + maxFileSize;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public String GenericExceptionHandler(Exception e) {
        return "Generic Exception occured" + e.getMessage();
    }

}
