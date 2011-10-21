package com.gordondickens.itemmanager.service;

import org.springframework.stereotype.Component;

/**
 * User: gordon
 * Date: 10/20/11
 * Time: 5:30 PM
 */
@Component
public class ItemServiceHandler {
    private static Integer count;

    public static void incrementServiceCount() {
        count++;
    }

}
