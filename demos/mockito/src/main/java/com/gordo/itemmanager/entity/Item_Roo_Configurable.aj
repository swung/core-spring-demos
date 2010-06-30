package com.gordo.itemmanager.entity;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect Item_Roo_Configurable {
    
    declare @type: Item: @Configurable;
    
}
