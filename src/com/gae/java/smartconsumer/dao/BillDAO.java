/**
 * BillDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Bill;

/**
 * Data access class for Bill object.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum BillDAO {
    /** Instance of class. */
    INSTANCE;

    /**
     * Get all bills from data store.
     * @return List of bills
     */
    public List<Bill> listBills() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Bill m");
        @SuppressWarnings("unchecked")
        List<Bill> bills = q.getResultList();
        return bills;
    }

    /**
     * Method get all bills sort by Id property.
     * @return List of bills sort by Id property.
     */
    public List<Bill> listBillsSortById() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from Bill m order by m.id desc");
        @SuppressWarnings("unchecked")
        List<Bill> bills = q.getResultList();
        return bills;
    }

    /**
     * Get Bill by Id.
     * @param id Id of Bill
     * @return Bill object has Id match
     */
    public Bill getBillById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Bill where id=" + id);
        Bill bill = (Bill) q.getSingleResult();
        return bill;
    }

    /**
     * Insert a bill to data store.
     * @param bill entity to insert
     */
    public void insert(Bill bill) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            //if (!isExist(deal)) {
                em.persist(bill);
            //}
            em.close();
        }
    }

    /**
     * Remove a record by Id.
     * @param id Id of record
     */
    public void delete(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Bill bill = em.find(Bill.class, id);
            em.remove(bill);
        } finally {
            em.close();
        }
    }

    /**
     * Update bill.
     * @param bill entity to update
     */
    public void update(Bill bill) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Bill innerBill = em.find(Bill.class, bill.getId());
            innerBill.setCustomerName(bill.getCustomerName());
            innerBill.setCustomerEmail(bill.getCustomerEmail());
            innerBill.setCustomerPhone(bill.getCustomerPhone());
            innerBill.setCustomerAddress(bill.getCustomerAddress());
            innerBill.setPayment(bill.getPayment());
            innerBill.setCardNumber(bill.getCardNumber());
            innerBill.setHolderName(bill.getHolderName());
            innerBill.setExpirationDate(bill.getExpirationDate());
        } finally {
            em.close();
        }
    }

    /**
     * Get max Id in Bill data.
     * @return Max Id if it is exist, null otherwise
     */
    public Long getMaxId() {
        if (listBillsSortById().size() == 0) {
            return (long) 0;
        } else {
            return listBillsSortById().get(0).getId();
        }
    }
}
