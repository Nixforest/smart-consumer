package com.gae.java.smartconsumer.dao;

import java.util.Timer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Deal;

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
    
    public List<Deal> listDealsSortByUpdateDate() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.updateDate desc");
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
                        String remainTime,
                        boolean isVoucher) throws Exception {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            Deal deal = new Deal(title, description, address, link,
                    imageLink, price, basicPrice, unitPrice, save, 
                    numberBuyer,
                    remainTime, 
                    isVoucher);
            if (!isExist(deal)) {   //
                em.persist(deal);
            }
            em.close();
        }
    }
    
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
    
    public void remove(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            em.remove(deal);
        } finally {
            // TODO: handle exception
            em.close();
        }
    }
    
    public boolean isExist(Deal deal) {
        for (Deal item : this.listDeals()) {
            if (deal.getTitle().equals(item.getTitle())) {
                return true;
            }
        }
        return false;        
    }
}
