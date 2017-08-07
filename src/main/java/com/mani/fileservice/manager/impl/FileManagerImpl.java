/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.exception.FileServiceException;
import com.mani.fileservice.manager.IFileManager;
import com.mani.fileservice.persistence.IFilePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */
@Component
public class FileManagerImpl implements IFileManager {

    @Autowired
    IFilePersistence fileStore;
    
    @Override
    public void uploadFile(MultipartFile file)  throws FileServiceException{
        fileStore.uploadFile(file);
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName) throws FileServiceException {
        return fileStore.downloadFile(fileName);
    }

}
