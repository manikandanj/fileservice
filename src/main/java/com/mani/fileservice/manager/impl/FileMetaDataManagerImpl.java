/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.manager.IFileMetaDataManager;
import com.mani.fileservice.persistence.db.IFileMetaDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */

@Component
public class FileMetaDataManagerImpl implements IFileMetaDataManager{
    
    @Autowired
    IFileMetaDataRepository fileMetaDataRepository;

    @Override
    public FileMetaData storeMetaData(MultipartFile file) {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setFileName(file.getOriginalFilename());
        fileMetaData.setFileSize(file.getSize());
        return fileMetaDataRepository.save(fileMetaData);
    }

    @Override
    public List<FileMetaData> fetchAllMetaData() {
        return fileMetaDataRepository.findAll();
    }
    
}
