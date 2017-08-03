/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */
public interface IFileManager {
    public void uploadFile(MultipartFile file);
}
