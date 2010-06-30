package com.gordo.itemmanager.entity;

import java.lang.String;

privileged aspect Item_Roo_JavaBean {
    
    public String Item.getName() {
        return this.name;
    }
    
    public void Item.setName(String name) {
        this.name = name;
    }
    
}
