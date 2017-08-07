/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.manager.impl;

import com.mani.fileservice.entity.FileMetaData;
import com.mani.fileservice.manager.IFileMetaDataManager;
import com.mani.fileservice.persistence.db.IFileMetaDataRepository;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mani
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileMetaDataManagerImplTest {

    @Autowired
    private IFileMetaDataManager metaDataMgr;
    
    @Autowired
    private IFileMetaDataRepository repository;

    public FileMetaDataManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void storeMetaDataTest() {
        MockMultipartFile file = new MockMultipartFile("file", "testfile.txt", "text/plain", "This is a mock file".getBytes());
        FileMetaData metaData = metaDataMgr.storeMetaData(file);
        assertEquals("testfile.txt", metaData.getFileName());
    }

    @Test
    public void fetchAllMetaDataTest() {
        MockMultipartFile file1 = new MockMultipartFile("file", "testfile1.txt", "text/plain", "Another mock file".getBytes());
        metaDataMgr.storeMetaData(file1);
        MockMultipartFile file2 = new MockMultipartFile("file", "testfile2.txt", "text/plain", "Yet another mock file".getBytes());
        metaDataMgr.storeMetaData(file2);
        List<FileMetaData> metaDataList = metaDataMgr.fetchAllMetaData();
        assertEquals("testfile1.txt", metaDataList.get(0).getFileName());
        assertEquals("testfile2.txt", metaDataList.get(1).getFileName());
    }

    @Test
    public void findMetaDataByFileIdTest() {
        MockMultipartFile file1 = new MockMultipartFile("file", "testfile1.txt", "text/plain", "Another mock file".getBytes());
        metaDataMgr.storeMetaData(file1);
        MockMultipartFile file2 = new MockMultipartFile("file", "testfile2.txt", "text/plain", "Yet another mock file".getBytes());
        metaDataMgr.storeMetaData(file2);
        FileMetaData metaData = metaDataMgr.findMetaDataByFileId(1L);
        assertEquals(Long.valueOf(1), metaData.getFileId());
    }

    @Test
    public void searchMetaDataTest() {
        MockMultipartFile file1 = new MockMultipartFile("file", "testfile1.txt", "text/plain", "Another mock file".getBytes());
        metaDataMgr.storeMetaData(file1);
        MockMultipartFile file2 = new MockMultipartFile("file", "testfile2.txt", "text/plain", "Yet another mock file".getBytes());
        metaDataMgr.storeMetaData(file2);

        FileMetaData searchMetaData1 = new FileMetaData();
        searchMetaData1.setFileName("testfile1");
        List<FileMetaData> metaDataResult = metaDataMgr.searchMetaData(searchMetaData1);
        assertEquals(1, metaDataResult.size());
        assertEquals("testfile1.txt", metaDataResult.get(0).getFileName());

        FileMetaData searchMetaData2 = new FileMetaData();
        searchMetaData2.setFileName("testfile");
        metaDataResult = metaDataMgr.searchMetaData(searchMetaData2);
        assertEquals(2, metaDataResult.size());
        assertEquals("testfile1.txt", metaDataResult.get(0).getFileName());
        assertEquals("testfile2.txt", metaDataResult.get(1).getFileName());
    }
}
