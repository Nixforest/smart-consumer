package com.gae.java.smartconsumer.dao;

import java.util.Timer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Deal;

public enum Dao {
    INSTANCE;
    
    public List<Deal> listDeals() {
        EntityManager em = EMFService.get().createEntityManager();
        
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m");
        List<Deal> deals = q.getResultList();
        
        return deals;
    }
    
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
                        //Timer remainTime,
                        boolean isVoucher) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            Deal deal = new Deal(title, description, address, link,
                    imageLink, price, basicPrice, unitPrice, save, 
                    numberBuyer,
                    //remainTime, 
                    isVoucher);
            em.persist(deal);
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
}
