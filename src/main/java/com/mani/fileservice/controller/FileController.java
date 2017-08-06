/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.controller;

import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.manager.IFileManager;
import com.mani.fileservice.manager.IFileMetaDataManager;
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
 *
 * @author mani
 */

@RestController
public class FileController {
    
    @Autowired
    IFileMetaDataManager fileMetaDataMgr;
    
    @Autowired
    IFileManager fileManager;
    
    //TODO Add more params for additional meta data
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public FileMetaData uploadFile(@RequestParam("file") MultipartFile file){
        fileManager.uploadFile(file);
        return fileMetaDataMgr.storeMetaData(file);
    }
    
    @RequestMapping(value="/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> fetchAllFiles(@RequestParam("fileId") Long fileId){
        FileMetaData fileMetaData = fileMetaDataMgr.findMetaDataByFileId(fileId);
        return fileManager.downloadFile(fileMetaData.getFileName());
    }
    
    
    @RequestMapping(value="/fetchAllMetaData", method = RequestMethod.GET)
    public List<FileMetaData> fetchAllFiles(){
        return fileMetaDataMgr.fetchAllMetaData();
    }
    
    @RequestMapping(value="/searchFiles", method = RequestMethod.GET)
    public List<FileMetaData> searchFiles(FileMetaData fileMetaData){
        return fileMetaDataMgr.searchMetaData(fileMetaData);
    }
    
}
