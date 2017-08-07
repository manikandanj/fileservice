/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.google.common.collect.Lists;
import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.entity.QFileMetaData;
import com.mani.fileservice.manager.IFileMetaDataManager;
import com.mani.fileservice.persistence.db.IFileMetaDataRepository;
import com.querydsl.core.BooleanBuilder;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Manager class for File meta data related operations
 * @author mani
 */
@Component
public class FileMetaDataManagerImpl implements IFileMetaDataManager {

    @Autowired
    IFileMetaDataRepository fileMetaDataRepository;

    @Override
    public FileMetaData storeMetaData(MultipartFile file) {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setFileName(file.getOriginalFilename());
        fileMetaData.setFileSize(file.getSize());
        fileMetaData.setCreatedDate(LocalDateTime.now());
        return this.storeMetaData(fileMetaData);
    }

    @Override
    public FileMetaData storeMetaData(FileMetaData metaData) {
        return fileMetaDataRepository.save(metaData);
    }

    @Override
    public List<FileMetaData> fetchAllMetaData() {
        return fileMetaDataRepository.findAll();
    }

    @Override
    public FileMetaData findMetaDataByFileId(Long fileId) {
        return fileMetaDataRepository.findOne(fileId);
    }

    @Override
    public List<FileMetaData> searchMetaData(FileMetaData metaData) {
        QFileMetaData fileMetaData = QFileMetaData.fileMetaData;
        BooleanBuilder condition = new BooleanBuilder();
        if (metaData.getFileId() != null) {
            condition.and(fileMetaData.fileId.eq(metaData.getFileId()));
        }
        if (metaData.getFileName() != null) {
            condition.and(fileMetaData.fileName.containsIgnoreCase(metaData.getFileName()));
        }
        if (metaData.getContentType() != null) {
            condition.and(fileMetaData.contentType.containsIgnoreCase(metaData.getContentType()));
        }
        if (metaData.getAuthor() != null) {
            condition.and(fileMetaData.author.containsIgnoreCase(metaData.getAuthor()));
        }
        if (metaData.getVersion() != null) {
            condition.and(fileMetaData.version.eq(metaData.getVersion()));
        }
        return Lists.newArrayList(fileMetaDataRepository.findAll(condition));
    }

}
