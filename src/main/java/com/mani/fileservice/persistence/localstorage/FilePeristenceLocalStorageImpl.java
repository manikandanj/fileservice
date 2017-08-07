/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.persistence.localstorage;

import com.mani.fileservice.exception.FileServiceException;
import com.mani.fileservice.persistence.IFilePersistence;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
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
public class FilePeristenceLocalStorageImpl implements IFilePersistence{

    @Value("${local.file.repo}")
    private String docRepoDir;

    @Override
    public void uploadFile(MultipartFile file) throws FileServiceException {
        Path fileRepoPath = Paths.get(docRepoDir);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), fileRepoPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileServiceException("IO Exception when uploading file", ex);
        }
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName) throws FileServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expiers", "0");
        headers.add("Content-Disposition", "attachment; fileName=" + fileName);

//        File file = new File(env.getProperty("local.file.repo") + fileName);
        File file = new File(docRepoDir + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new FileServiceException("IO Exception when downloading file", ex);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
