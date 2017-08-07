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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author mani
 */
@Component
public class FilePollManagerImpl implements IFilePollManager {

    @Autowired
    IFileMetaDataRepository fileMetaDataRepository;

    @Autowired
    IEmailGateway emailGateway;

    @Value("${spring.mail.username}")
    String emailFrom;

    @Value("${report.email.to}")
    String emailTo;

    @Value("${report.email.subject}")
    String subject;

    @Value("${poll.interval.min}")
    Long pollInterValMin;

    @Override
    //Temporarily setting to 1 minute interval for testing
    @Scheduled(fixedRate = 60 * 1000)
    public void pollRecentFiles() {
        List<FileMetaData> metaDataList = fileMetaDataRepository.findByCreatedDateAfter(LocalDateTime.now().minusMinutes(pollInterValMin));
        if (metaDataList.size() > 0) {
            this.triggerEmail(metaDataList);
        }
    }

    private void triggerEmail(List<FileMetaData> fileMetaDataList) {
        StringBuilder emailText = new StringBuilder("Hi, \r\nBelow are the files which are added recently - \r\n \r\n");
        for (FileMetaData metaData : fileMetaDataList) {
            emailText.append(metaData.getFileName()).append("\r\n");
        }
        //TODO Move these to properties file.
        emailGateway.sendEmail("file.report.bot@gmail.com", "file.report.bot@gmail.com", "File upload report", emailText.toString());
    }

}
