package com.gordondickens.jmsdemo.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public class Recipient implements Serializable {

	private static final long serialVersionUID = 1L;

	private String senderEmail;

    private String senderFirstName;

    private String senderLastName;

    private String recipientEmail;

    private String recipientFirstName;

    private String recipientLastName;

    private String subject;

    private String messageBody;
}
