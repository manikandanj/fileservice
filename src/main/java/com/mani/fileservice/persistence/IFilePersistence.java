/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.persistence;

import com.mani.fileservice.exception.FileServiceException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */
public interface IFilePersistence {

    public void uploadFile(MultipartFile file) throws FileServiceException;

    public ResponseEntity<Resource> downloadFile(String fileName) throws FileServiceException;
}
