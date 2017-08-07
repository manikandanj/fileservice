/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.exception.FileServiceException;
import com.mani.fileservice.manager.IFileManager;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mani
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileManagerImplTest {

    @Autowired
    IFileManager fileManager;

    public FileManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void uploadTest() {
        try {
            MockMultipartFile file = new MockMultipartFile("file", "file1.txt", "text/plain", "This is a mock file".getBytes());
            fileManager.uploadFile(file);
        } catch (FileServiceException ex) {
            fail("FileServiceException");
        } catch (Exception ex) {
            fail("Exception");
        }
    }

    @Test
    public void downloadTest() {
        try {
            String uploadedContent = "This is a mock file";
            MockMultipartFile file = new MockMultipartFile("file", "file1.txt", "text/plain", uploadedContent.getBytes());
            fileManager.uploadFile(file);
            ResponseEntity<Resource> res = fileManager.downloadFile("file1.txt");
             String downloadedContent = IOUtils.toString(res.getBody().getInputStream(), StandardCharsets.UTF_8);
             assertEquals(uploadedContent, downloadedContent);
        } catch (FileServiceException ex) {
            fail("FileServiceException");
        } catch (Exception ex) {
            fail("Exception");
        }
    }
}
