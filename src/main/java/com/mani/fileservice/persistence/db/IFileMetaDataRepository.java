/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.persistence.db;

import com.mani.fileservice.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mani
 */
public interface IFileMetaDataRepository extends JpaRepository<FileMetaData, Long>{
    
}
