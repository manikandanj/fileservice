/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.persistence.db;

import com.mani.fileservice.entity.FileMetaData;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 *
 * @author mani
 */
public interface IFileMetaDataRepository extends JpaRepository<FileMetaData, Long>, QueryDslPredicateExecutor{
    public List<FileMetaData> findByCreatedDateAfter(LocalDateTime dateTime);
}
