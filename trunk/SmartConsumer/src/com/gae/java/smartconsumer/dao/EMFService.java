/**
 * EMFService.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class create connection for access data store.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public final class EMFService {
    /**
     * EMF instance.
     */
    private static final EntityManagerFactory EMFINSTANCE = Persistence
            .createEntityManagerFactory("transactions-optional");
    /**
     * Constructor.
     */
    private EMFService() {
    }
    /**
     * Get EMF instance.
     * @return EMF Instance
     */
    public static EntityManagerFactory get() {
        return EMFINSTANCE;
    }
}
