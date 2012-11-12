/**
 * AddressDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Address;

/**
 * Data access class for Address object.
 * @version 1.0 28/05/2012
 * @version 2.0 15/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum AddressDAO {
    /** Instance of class. */
    INSTANCE;
    /** List all addresses in this session. */
    private List<Address> listAllAddresses = new ArrayList<Address>();
    /** List all addresses has just inserted in this session. */
    private List<Address> listInsertAddresses = new ArrayList<Address>();
    /**
     * Get all addresses in data store.
     * If listAddresses variable is null,
     * create a connection to data store.
     * @return List of Addresses
     */
    @SuppressWarnings("unchecked")
    public List<Address> getListAllAddresses() {
        if (this.listAllAddresses.isEmpty()) {
            EntityManager em = EMFService.get().createEntityManager();
            // Read the existing entries
            Query q = em.createQuery("select m from Address m");
            List<Address> addresses = q.getResultList();
            for (Address address : addresses) {
                this.listAllAddresses.add(address);
            }
        }
        return this.listAllAddresses;
    }
    /**
     * Insert a address to template variable, not insert to data store yet.
     * @param address Address to insert
     */
    public void insert(Address address) {
        // Insert to list insert addresses
        this.listInsertAddresses.add(address);
        // Insert to list all addresses
        this.listAllAddresses.add(address);
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                em.persist(address);
            } finally {
                em.close();
            }
        }
    }
    /**
     * Insert list insert addresses into data store.
     */
    public void insertIntoDatastore() {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                for (Address address : this.listInsertAddresses) {
                    em.persist(address);
                }
                this.listInsertAddresses.clear();
            } finally {
                em.close();
            }
        }
    }
    /**
     * Delete.
     * @param id id of address to delete
     */
    public void delete(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Address address = em.find(Address.class, id);
            em.remove(address);
        } finally {
            em.close();
        }
    }
}
