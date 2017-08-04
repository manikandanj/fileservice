/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mani.fileservice.gateway.email;

/**
 *
 * @author mani
 */
public interface IEmailGateway {
    //TODO Check if we need a bean for Email
    public void sendEmail(String from, String to, String subject, String body);
}
