/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager;

import com.mani.fileservice.entity.FileMetaData;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mani
 */

public interface IFileMetaDataManager {
    public FileMetaData storeMetaData(MultipartFile file);
    public List<FileMetaData> fetchAllMetaData();
}
