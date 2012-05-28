/**
 * EMFService.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class create connection for access data store
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class EMFService {
    private static final EntityManagerFactory emfInstance = Persistence
            .createEntityManagerFactory("transactions-optional");

    private EMFService() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
