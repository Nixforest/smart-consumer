/**
 * BillDetailDAO.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.BillDetail;

/**
 * Data access class for Bill Detail object
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum BillDetailDAO {
    INSTANCE;
    
    /**
     * Get all bill detail from data store.
     * @return List of bill details
     */
    public List<BillDetail> listBillDetails() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from BillDetail m");
        @SuppressWarnings("unchecked")
        List<BillDetail> billDetails = q.getResultList();
        
        return billDetails;
    }
    
    /**
     * Method get all bill detail sort by Id property.
     * @return List of bill detail sort by Id property.
     */
    public List<BillDetail> listBillDetailsSortById() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from BillDetail m order by m.id desc");
        @SuppressWarnings("unchecked")
        List<BillDetail> details = q.getResultList();
        return details;
    }
    
    /**
     * Method get Bill Detail object from Id.
     * @param id the value of Id wish to get
     * @return BillDetail object has Id match parameter
     */
    public BillDetail getBillDetailById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from BillDetail where id=" + id);
        
        BillDetail billDetail = (BillDetail)q.getSingleResult();
        return billDetail;
    }
    
    /**
     * Insert a Bill Detail.
     * @param billDetail object to insert
     */
    public void insert(BillDetail billDetail) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            //if (!isExist(deal)) {
                em.persist(billDetail);
            //}
            em.close();
        }
    }
    
    /**
     * Get max Id in Bill's detail data.
     * @return Max Id if it is exist, null otherwise
     */
    public Long getMaxId() {
        if (listBillDetailsSortById().size() == 0) {
            return (long)0;
        } else {
            return listBillDetailsSortById().get(0).getId();
        }
    }
}
