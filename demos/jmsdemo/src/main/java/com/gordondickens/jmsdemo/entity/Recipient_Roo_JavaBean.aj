package com.gordondickens.jmsdemo.entity;

import java.lang.String;

privileged aspect Recipient_Roo_JavaBean {
    
    public String Recipient.getSenderEmail() {
        return this.senderEmail;
    }
    
    public void Recipient.setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    
    public String Recipient.getSenderFirstName() {
        return this.senderFirstName;
    }
    
    public void Recipient.setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }
    
    public String Recipient.getSenderLastName() {
        return this.senderLastName;
    }
    
    public void Recipient.setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }
    
    public String Recipient.getRecipientEmail() {
        return this.recipientEmail;
    }
    
    public void Recipient.setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
    
    public String Recipient.getRecipientFirstName() {
        return this.recipientFirstName;
    }
    
    public void Recipient.setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }
    
    public String Recipient.getRecipientLastName() {
        return this.recipientLastName;
    }
    
    public void Recipient.setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }
    
    public String Recipient.getSubject() {
        return this.subject;
    }
    
    public void Recipient.setSubject(String subject) {
        this.subject = subject;
    }
    
    public String Recipient.getMessageBody() {
        return this.messageBody;
    }
    
    public void Recipient.setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
}
