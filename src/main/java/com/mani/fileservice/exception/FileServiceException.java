/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.exception;

/**
 *
 * @author mani
 */
public class FileServiceException extends Exception {

    /**
     * Creates a new instance of <code>FileServiceException</code> without
     * detail message.
     */
    public FileServiceException() {
    }

    /**
     * Constructs an instance of <code>FileServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FileServiceException(String msg) {
        super(msg);
    }
    
     public FileServiceException(String msg, Throwable t) {
        super(msg, t);
    }
}
