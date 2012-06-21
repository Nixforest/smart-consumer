/**
 * AddressDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Address;

/**
 * Data access class for Deal object.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum AddressDAO {
    /** Instance of class. */
    INSTANCE;

    /**
     * Get all addresses in data store.
     * @return List of Addresses
     */
    public List<Address> listAddresses() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Address m");
        @SuppressWarnings("unchecked")
        List<Address> addresses = q.getResultList();
        return addresses;
    }

    /**
     * Get all addresses in data store.
     * @return List of addresses sort by Id property
     */
    public List<Address> listAddressesSortById() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Address m order by m.id desc");
        @SuppressWarnings("unchecked")
        List<Address> addresses = q.getResultList();
        return addresses;
    }

    /**
     * Get address by Id.
     * @param id id of address
     * @return Address has Id match parameter
     */
    public Address getAddressById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Address.class.getName() + " where id=" + id);
        Address address = (Address) q.getSingleResult();
        return address;
    }

    /**
     * Insert address.
     * @param address address to insert
     */
    public void insert(Address address) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            em.persist(address);
            em.close();
        }
    }

    /**
     * Update address.
     * @param address address to update
     */
    public void update(Address address) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Address innerAddress = em.find(Address.class, address.getId());
            innerAddress.setFullAddress(address.getFullAddress());
            innerAddress.setLongitude(address.getLongitude());
            innerAddress.setLatitude(address.getLatitude());
            innerAddress.setDescription(address.getDescription());
        } finally {
            em.close();
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

    /**
     * Get max id in Address data.
     * @return Max id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        if (listAddressesSortById().size() == 0) {
            return (long) 0;
        } else {
            return listAddressesSortById().get(0).getId();
        }
    }
}
