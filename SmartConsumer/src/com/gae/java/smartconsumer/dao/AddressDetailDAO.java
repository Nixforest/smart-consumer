/**
 * AddressDetailDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.AddressDetail;

/**
 * Data access class for AddressDetail object.
 * @version 1.0 28/05/2012 - Update - NguyenPT
 * @version 2.0 13/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum AddressDetailDAO {
    /** Instance of class. */
    INSTANCE;
    /** List all address details in this session. */
    private List<AddressDetail> listAllAddressDetails = new ArrayList<AddressDetail>();
    /** List all address details has just inserted in this session. */
    private List<AddressDetail> listInsertAddressDetails = new ArrayList<AddressDetail>();
    /**
     * Get all address detail from data store.
     * @return List of AddressDetails
     */
    @SuppressWarnings("unchecked")
    public List<AddressDetail> getListAllAddressDetails() {
        if (this.listAllAddressDetails.isEmpty()) {
            EntityManager em = EMFService.get().createEntityManager();
            // Read the existing entries
            Query q = em.createQuery("select m from AddressDetail m");
            this.listAllAddressDetails = q.getResultList();
        }
        return this.listAllAddressDetails;
    }
    /**
     * Insert method.
     * @param detail AddressDetail to insert
     */
    public void insert(AddressDetail detail) {
        // Insert to list insert address details
        this.listInsertAddressDetails.add(detail);
        // Insert to list all address details
        this.listAllAddressDetails.add(detail);
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                em.persist(detail);
            } finally {
                em.close();
            }
        }
    }
    /**
     * Insert list insert address details into data store.
     */
    public void insertIntoDatastore() {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                for (AddressDetail detail : this.listInsertAddressDetails) {
                    em.persist(detail);
                }
                this.listInsertAddressDetails.clear();
            } finally {
                em.close();
            }
        }
    }
    /**
     * Delete method.
     * @param id Id of AddressDetail
     */
    public void delete(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            AddressDetail detail = em.find(AddressDetail.class, id);
            em.remove(detail);
        } finally {
            em.close();
        }
    }
}
