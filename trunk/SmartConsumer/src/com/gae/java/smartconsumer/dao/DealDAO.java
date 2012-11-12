/**
 * DealDAO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

/**
 * Data access class for Deal object.
 * @version 2.0 03/06/2012 - Update - NguyenPT
 * @version 2.0 13/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum DealDAO {
    /** Instance of class. */
    INSTANCE;
    /** List all deals from data store has Status is SELLING. */
    private List<Deal> listActiveDeals = new ArrayList<Deal>();
    /** List all deals from data store has Status is not SELLING. */
    private List<Deal> listInActiveDeals = new ArrayList<Deal>();
    /** List all deals has just inserted in this session. */
    private List<Deal> listInsertDeals = new ArrayList<Deal>();
    /** List all deals has just updated in this session. */
    private List<Deal> listUpdateDeals = new ArrayList<Deal>();
    /** List all deals in this session. */
    private List<Deal> listAllDeals = new ArrayList<Deal>();
    /**
     * Get all deal selling from data store.
     * If listActiveDeals variable is null,
     * create a connection to data store.
     * @return List of Deals
     */
    @SuppressWarnings("unchecked")
    public List<Deal> getListActiveDeals() {
        if (this.listActiveDeals.isEmpty()) {
            EntityManager em = EMFService.get().createEntityManager();
            Query q = em.createQuery("select from " + Deal.class.getName() + " where status="
                    + Status.SELLING.ordinal());
            List<Deal> deals = q.getResultList();
            for (Deal deal : deals) {
                this.listActiveDeals.add(deal);
            }
        }
        return this.listActiveDeals;
    }
    /**
     * Get all deal is not selling from data store.
     * If listInActiveDeals variable is null,
     * create a connection to data store.
     * @return List of Deals
     */
    @SuppressWarnings("unchecked")
    public List<Deal> getListInActiveDeals() {
        if (this.listInActiveDeals.isEmpty()) {
            EntityManager em = EMFService.get().createEntityManager();
            Query q = em.createQuery("select from " + Deal.class.getName() + " where status<>"
                    + Status.SELLING.ordinal());
            List<Deal> deals = q.getResultList();
            for (Deal deal : deals) {
                this.listInActiveDeals.add(deal);
            }
        }
        return this.listInActiveDeals;
    }
    /**
     * Get all deals from data store.
     * @return List of Deals
     */
    public List<Deal> getListAllDeals() {
        if (this.listAllDeals.isEmpty()) {
            for (Deal deal : this.getListActiveDeals()) {
                this.listAllDeals.add(deal);
            }
            for (Deal deal : this.getListInActiveDeals()) {
                this.listAllDeals.add(deal);
            }
        }
        return this.listAllDeals;
    }
    /**
     * Insert a deal to template variable, not insert to data store yet.
     * @param deal Entity to insert
     */
    public void insert(Deal deal) {
        // Insert to List insert deals
        this.listInsertDeals.add(deal);
        // Insert to List active or inactive deals (depend on Status property)
        if (deal.getStatus() == Status.SELLING.ordinal()) {
            this.listActiveDeals.add(deal);
        } else {
            this.listInActiveDeals.add(deal);
        }
        // Insert to List all deals
        this.listAllDeals.add(deal);
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                    em.persist(deal);
            } finally {
                em.close();
            }
        }
    }
    /**
     * Insert list insert deals into data store.
     */
    public void insertIntoDatastore() {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                for (Deal deal : this.listInsertDeals) {
                    em.persist(deal);
                }
                this.listInsertDeals.clear();
            } finally {
                em.close();
            }
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
     * Method change status of deal.
     * @param deal Deal to change status
     * @param changeToStatus status that change to
     */
    public void changeStatus(Deal deal, int changeToStatus) {
        if (changeToStatus != deal.getStatus()) {
            Deal dealToChange = deal;
            // Change status
            dealToChange.setStatus(changeToStatus);
            // If change to SELLING -> Add to list active and remove from inactive list
            if (changeToStatus == Status.SELLING.ordinal()) {
                this.listActiveDeals.add(dealToChange);
                this.listInActiveDeals.remove(deal);
            } else if (deal.getStatus() == Status.SELLING.ordinal()) {
                // If change from SELLING -> Add to inactive list and remove from active list
                this.listActiveDeals.remove(deal);
                this.listInActiveDeals.add(dealToChange);
            }
            // Remove old deal from List all deals and add new deal to this list
            this.listAllDeals.remove(deal);
            this.listAllDeals.add(dealToChange);
        }
    }
    /**
     * Check if a deal exist.
     * @param deal object need to check
     * @return True if deal has a link exist in data store and
     * deal has status not DELETED, false otherwise.
     */
    public boolean isExist(Deal deal) {
        for (Deal item : this.listAllDeals) {
            if (deal.getLink().equals(item.getLink())
                    && (deal.getStatus() != Status.DELETED.ordinal())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Update a deal to a list of deals.
     * @param deal Deal to update
     * @param listDeals List deals
     * @return True if update action success, false otherwise
     */
    public boolean updateDealToList(Deal deal, List<Deal> listDeals) {
        for (Deal item : listDeals) {
            if (deal.getId().equals(item.getId())) {
                item.setTitle(deal.getTitle());
                item.setDescription(deal.getDescription());
                item.setLink(deal.getLink());
                item.setImageLink(deal.getImageLink());
                item.setPrice(deal.getPrice());
                item.setBasicPrice(deal.getBasicPrice());
                item.setUnitPrice(deal.getUnitPrice());
                item.setSave(deal.getSave());
                item.setNumberBuyer(deal.getNumberBuyer());
                item.setEndTime(deal.getEndTime());
                item.setVoucher(deal.getisVoucher());
                item.setUpdateDate(deal.getUpdateDate());
                item.setStatus(deal.getStatus());
                return true;
            }
        }
        return false;
    }
    /**
     * Update deal to template variable, not update to data store yet.
     * @param deal deal wish to update
     */
    public void update(Deal deal) {
        // Insert deal need to update to List update deals
        this.listUpdateDeals.add(deal);
        // Update this deal to List active or inactive deals (depend on Status Property)
        if (deal.getStatus() == Status.SELLING.ordinal()) {
            this.updateDealToList(deal, this.listActiveDeals);
        } else {
            this.updateDealToList(deal, this.listInActiveDeals);
        }
        // Update this deal to List all deals
        this.updateDealToList(deal, this.listAllDeals);
    }
    /**
     * Insert list update deals into data store.
     */
    public void updateIntoDatastore() {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            for (Deal deal : this.listUpdateDeals) {
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
            }
            this.listUpdateDeals.clear();
        } finally {
            // Close connection
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
     * Remove all record in deal table.
     */
    public void removeAll() {
        EntityManager em = EMFService.get().createEntityManager();
        // Remove all record in table Deal
        try {
            for (Deal deal : this.listAllDeals) {
                Deal dealx = em.find(Deal.class, deal.getId());
                em.remove(dealx);
            }
        } finally {
            em.close();
        }
    }
}
