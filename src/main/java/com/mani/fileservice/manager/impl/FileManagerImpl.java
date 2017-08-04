/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.manager.IFileManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */

@Component
public class FileManagerImpl implements IFileManager {
    
    //TODO Get path from properties file
    final String FILE_REPO = "/home/mani/temp/docrepo/";

    @Override
    public void uploadFile(MultipartFile file) {
        Path fileRepoPath = Paths.get(FILE_REPO);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), fileRepoPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName) {
        //TODO Revisit design. If it's a good place to keep HttpHeaders in Manager
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control","no-cache, no-store must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expiers","0");
        headers.add("Content-Disposition", "attachment; fileName=" + fileName);
        
        File file = new File(FILE_REPO+fileName);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException ex) {
            Logger.getLogger(FileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
}
