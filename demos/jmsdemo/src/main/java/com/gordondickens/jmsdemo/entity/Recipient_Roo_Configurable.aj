package com.gordondickens.jmsdemo.entity;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect Recipient_Roo_Configurable {
    
    declare @type: Recipient: @Configurable;
    
}
