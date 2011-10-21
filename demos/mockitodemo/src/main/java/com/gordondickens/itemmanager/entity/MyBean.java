package com.gordondickens.itemmanager.entity;

import com.gordondickens.itemmanager.service.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyBean {
    private static final Logger logger = LoggerFactory.getLogger(MyBean.class);

    public long createData() {
        long value = IdGenerator.generateNewId();
        logger.debug("Generated id '{}'", value);
        return value;
    }
}
