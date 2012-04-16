package com.gae.java.smartconsumer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

public enum DealDAO {
    INSTANCE;
    
    /**
     * Get all deal from table Deal
     * [Give the description for method].
     * @return List of Deals
     */
    public List<Deal> listDeals() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m");
        List<Deal> deals = q.getResultList();
        
        return deals;
    }
    
    /**
     * 
     * Method get all deals.
     * @return List of deals sort by updatedate
     */
    public List<Deal> listDealsSortByUpdateDate() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.updateDate desc");
        List<Deal> deals = q.getResultList();
        
        return deals;
    }
    
    /**
     * 
     * Method get all deals.
     * @return List of deals sort by EndTime
     */
    public List<Deal> listDealsSortByEndTime() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.endTime desc");
        List<Deal> deals = q.getResultList();
        
        return deals;
    }
    
    /**
     * Insert a deal to table Deal
     * [Give the description for method].
     * @param title
     * @param description
     * @param address
     * @param link
     * @param imageLink
     * @param price
     * @param basicPrice
     * @param unitPrice
     * @param save
     * @param numberBuyer
     * @param remainTime
     * @param isVoucher
     * @throws Exception
     */    
    public void insert(String title,
                        String description,
                        String address,
                        String link,
                        String imageLink,
                        double price,
                        double basicPrice,
                        String unitPrice,
                        float save,
                        int numberBuyer,
                        Date endTime,
                        boolean isVoucher) throws Exception {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            Deal deal = new Deal(title, description, address, link,
                    imageLink, price, basicPrice, unitPrice, save, 
                    numberBuyer,
                    endTime,
                    isVoucher);
            if (!isExist(deal)) {   //
                em.persist(deal);
            }
            em.close();
        }
    }
    
    /**
     * 
     * Remove all record in deal table.
     */
    public void removeAll() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Remove all record in table Deal
        try {
            for (Deal deal : listDeals()) {
                Deal dealx = em.find(Deal.class, deal.getId());
                em.remove(dealx);
            }    
        } finally {
            em.close();
        }
        
    }
    
    /**
     * 
     * Remove a record by ID.
     * @param id id of record
     */
    public void delete(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            em.remove(deal);
        } finally {
            // TODO: handle exception
            em.close();
        }
    }    
    /**
     * 
     * Method represent a delete method by change Status of deal to DELETE.
     * @param id object's id
     */
    public void deleteByChangeStatus(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            deal.setStatus(Status.DELETE.ordinal());
            //em.persist(deal);
        } finally {
            em.close();
        }
    }
    /**
     * 
     * Method represent a restore method by change Status of deal to SELLING.
     * @param id object's id
     */
    public void restoreChangeStatus(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            deal.setStatus(Status.SELLING.ordinal());
            //em.persist(deal);
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * Check if a deal exist.
     * @param deal object need to check
     * @return True if deal existed, false otherwise.
     */
    public boolean isExist(Deal deal) {
        for (Deal item : this.listDeals()) {
            if (deal.getLink().equals(item.getLink())) {
                return true;
            }
        }
        return false;        
    }
}
