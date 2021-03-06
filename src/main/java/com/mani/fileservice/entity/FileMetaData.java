/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

/**
 * Entity object to store File Meta data
 * @author mani
 */

@Entity
@Table(name = "file_meta_data")
@Data
public class FileMetaData implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long fileId;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "content_type")
    private String contentType;
    
    @Column(name = "file_size")
    private long fileSize;
    
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    
    private String author;
    
    private Float version;
}
