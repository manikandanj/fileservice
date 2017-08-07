/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.controller;

import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.exception.FileServiceException;
import com.mani.fileservice.gateway.email.IEmailGateway;
import com.mani.fileservice.manager.IFileManager;
import com.mani.fileservice.manager.IFileMetaDataManager;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Main Rest controller
 * @author mani
 */
@RestController
public class FileController {

    @Autowired
    IFileMetaDataManager fileMetaDataMgr;

    @Autowired
    IFileManager fileManager;

    @Autowired
    IEmailGateway emailGateway;

    /**
     * This method serves as the end point to upload file
     * @param file File to be uploaded
     * @param author (Optional)Author of file (meta data)
     * @param version (Optional) file version
     * @return Meta data of the file that was uploaded
     * @throws FileServiceException 
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public FileMetaData uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "author", required = false) String author, @RequestParam(value = "version", required = false) Float version) throws FileServiceException {
        fileManager.uploadFile(file);
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setContentType(file.getContentType());
        fileMetaData.setFileName(file.getOriginalFilename());
        fileMetaData.setFileSize(file.getSize());
        fileMetaData.setCreatedDate(LocalDateTime.now());
        fileMetaData.setAuthor(author);
        fileMetaData.setVersion(version);
        return fileMetaDataMgr.storeMetaData(fileMetaData);
    }

    /**
     * This method serves as the end point to download file
     * @param fileId File Id (generated primary key) for the file to be downloaded
     * @return File download
     * @throws FileServiceException 
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam("fileId") Long fileId) throws FileServiceException {
        FileMetaData fileMetaData = fileMetaDataMgr.findMetaDataByFileId(fileId);
        return fileManager.downloadFile(fileMetaData.getFileName());
    }

    /**
     * This method serves as the end point to fetch all metadata
     * @return List of File meta data
     */
    @RequestMapping(value = "/fetchAllMetaData", method = RequestMethod.GET)
    public List<FileMetaData> fetchAllFiles() {
        return fileMetaDataMgr.fetchAllMetaData();
    }

    /**
     * This method serves as the end point to search for files using meta data fields
     * @param fileMetaData Search input fields. All fields are optional.
     * @return List of file meta data matching the search conditions
     */
    @RequestMapping(value = "/searchFiles", method = RequestMethod.GET)
    public List<FileMetaData> searchFiles(FileMetaData fileMetaData) {
        return fileMetaDataMgr.searchMetaData(fileMetaData);
    }

}
