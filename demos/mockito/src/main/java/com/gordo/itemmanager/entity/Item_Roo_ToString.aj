package com.gordo.itemmanager.entity;

import java.lang.String;

privileged aspect Item_Roo_ToString {
    
    public String Item.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName());
        return sb.toString();
    }
    
}
