/**
 * AddressDetailDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Data access class for AddressDetail object.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum AddressDetailDAO {
    /** Instance of class. */
    INSTANCE;
    /**
     * Get all address detail from data store.
     * @return List of AddressDetails
     */
    public List<AddressDetail> listAddressDetails() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from AddressDetail m");
        @SuppressWarnings("unchecked")
        List<AddressDetail> details = q.getResultList();
        return details;
    }
    /**
     * Method get all address detail sort by Id property.
     * @return List of AddressDetails sort by Id property.
     */
    public List<AddressDetail> listAddressDetailsSortById() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from AddressDetail m order by m.id desc");
        @SuppressWarnings("unchecked")
        List<AddressDetail> details = q.getResultList();
        return details;
    }
    /**
     * Get AddressDetail by Id.
     * @param id Id of AddressDetail
     * @return AddressDetail object has Id match
     */
    public AddressDetail getAddressDetailById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Deal.class.getName() + " where id=" + id);
        AddressDetail detail = (AddressDetail) q.getSingleResult();
        return detail;
    }
    /**
     * Insert method.
     * @param detail AddressDetail to insert
     */
    public void insert(AddressDetail detail) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            em.persist(detail);
            em.close();
        }
    }
    /**
     * Update method.
     * @param detail AddressDetail to update
     */
    public void update(AddressDetail detail) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            AddressDetail innerDetail = em.find(AddressDetail.class, detail.getId());
            innerDetail.setDealId(detail.getDealId());
            innerDetail.setAddressId(detail.getAddressId());
        } finally {
            em.close();
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
    /**
     * Get max Id in Deal data.
     * @return Max Id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        if (listAddressDetailsSortById().size() == 0) {
            return (long) 0;
        } else {
            return listAddressDetailsSortById().get(0).getId();
        }
    }
}
