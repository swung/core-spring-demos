package com.gordondickens.simail.entity;

import org.springframework.roo.addon.entity.RooJpaEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Recipient {

    private String recipientEmail;

    private String subject;

    private String messageBody;
}
