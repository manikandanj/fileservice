/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.gateway.email.IEmailGateway;
import com.mani.fileservice.manager.IFilePollManager;
import com.mani.fileservice.persistence.db.IFileMetaDataRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author mani
 */

@Component
public class FilePollManagerImpl implements IFilePollManager{

    @Autowired
    IFileMetaDataRepository fileMetaDataRepository;
    
    @Autowired
    IEmailGateway emailGateway;
    
    @Override
    @Scheduled(fixedRate = 60*1000)
    public void pollRecentFiles() {
        //Temporarily setting to 1 minute interval for testing
        List<FileMetaData> metaDataList = fileMetaDataRepository.findByCreatedDateAfter(LocalDateTime.now().minusMinutes(1));
        if(metaDataList.size() > 0){
            this.triggerEmail(metaDataList);
        }
    }
    
    private void triggerEmail(List<FileMetaData> fileMetaDataList){
        StringBuilder emailText = new StringBuilder("Hi, \r\nBelow are the files which are added recently - \r\n \r\n");
        for(FileMetaData metaData:fileMetaDataList){
            emailText.append(metaData.getFileName()).append("\r\n");
        }
        //TODO Move these to properties file.
        emailGateway.sendEmail("file.report.bot@gmail.com", "file.report.bot@gmail.com", "File upload report", emailText.toString());
        System.out.println("********************* Sending email :" + emailText);
    }
    
}
