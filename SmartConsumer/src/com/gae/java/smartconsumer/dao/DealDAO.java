/**
 * DealDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

/**
 * Data access class for Deal object.
 * @version 2.0 3/6/2012
 * @author Nixforest
 * @update Khoa
 */
public enum DealDAO {
    /** Instance of class. */
    INSTANCE;
    /**
     * Get all deal from data store.
     * @return List of Deals
     */
    public List<Deal> listDeals() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m");
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }
    /**
     * Get all deal selling from data store.
     * @return List of Deals
     */
    public List<Deal> listDealsSelling(){
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Deal.class.getName() + " where status=" + Status.SELLING.ordinal());
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }   
    /**
     * 
     * limit get deal
     * @param limit
     * @return List<Deal>
     */
    public List<Deal> listDealsLimit(int limit) {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.endTime desc");
        q.setMaxResults(limit);
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }
    /**
     * Method get all deals sort by updateDate property.
     * @return List of deals sort by updateDate property.
     */
    public List<Deal> listDealsSortByUpdateDate() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.updateDate desc");
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }

    /**
     * Method get all deals sort by EndTime property.
     * @return List of deals sort by EndTime property.
     */
    public List<Deal> listDealsSortByEndTime() {
        EntityManager em = EMFService.get().createEntityManager();
        // Read the existing entries
        Query q = em.createQuery("select m from Deal m order by m.endTime desc");
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }

    /**
     * Method get all deals sort by Id property.
     * @return List of deals sort by Id property.
     */
    public List<Deal> listDealsSortById() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from Deal m order by m.id desc");
        @SuppressWarnings("unchecked")
        List<Deal> deals = q.getResultList();
        return deals;
    }

    /**
     * Get Deal by Id.
     * @param id Id of Deal
     * @return Deal object has Id match
     */
    public Deal getDealById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Deal.class.getName() + " where id=" + id);
        Deal deal = (Deal) q.getSingleResult();
        return deal;
    }

    /**
     * Insert a deal to data store.
     * @param deal entity to insert
     */
    public void insert(Deal deal) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            em.persist(deal);
            em.close();
        }
    }

    /**
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
     * Remove a record by Id.
     * @param id id of record
     */
    public void delete(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            em.remove(deal);
        } finally {
            em.close();
        }
    }

    /**
     * Method change status of deal.
     * @param id id of deal
     * @param changeToStatus status that change to
     */
    public void changeStatus(long id, int changeToStatus) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal deal = em.find(Deal.class, id);
            deal.setStatus(changeToStatus);
        } finally {
            em.close();
        }
    }

    /**
     * Update deal.
     * @param deal deal wish to update
     */
    public void update(Deal deal) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal innerDeal = em.find(Deal.class, deal.getId());
            innerDeal.setTitle(deal.getTitle());
            innerDeal.setDescription(deal.getDescription());
            innerDeal.setLink(deal.getLink());
            innerDeal.setImageLink(deal.getImageLink());
            innerDeal.setPrice(deal.getPrice());
            innerDeal.setBasicPrice(deal.getBasicPrice());
            innerDeal.setUnitPrice(deal.getUnitPrice());
            innerDeal.setSave(deal.getSave());
            innerDeal.setNumberBuyer(deal.getNumberBuyer());
            innerDeal.setEndTime(deal.getEndTime());
            innerDeal.setVoucher(deal.getisVoucher());
            innerDeal.setUpdateDate(deal.getUpdateDate());
            innerDeal.setStatus(deal.getStatus());
        } finally {
            // Close connection
            em.close();
        }
    }

    /**
     * Check if a deal exist.
     * @param deal object need to check
     * @return True if deal has a link exist in data store and deal has status not DELETED, false otherwise.
     */
    public boolean isExist(Deal deal) {
        for (Deal item : this.listDeals()) {
            if (deal.getLink().equals(item.getLink()) && (deal.getStatus() != Status.DELETED.ordinal())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get max Id in Deal data.
     * @return Max Id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        if (listDealsSortById().size() == 0) {
            return (long) 0;
        } else {
            return listDealsSortById().get(0).getId();
        }
    }
}
