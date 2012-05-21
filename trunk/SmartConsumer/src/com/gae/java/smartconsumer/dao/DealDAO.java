/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gae.java.smartconsumer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.Status;
import com.google.appengine.api.datastore.Text;

/**
 * @author Nixforest
 *
 */
public enum DealDAO {
    INSTANCE;
    
    /**
     * Get all deal from datastore
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
     * Method get all deals soft by updateDate property.
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
     * Method get all deals soft by EndTime property.
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
     * Get Deal by Id
     * @param id Id of Deal
     * @return Deal object has Id match
     */
    public Deal getDealById(Long id){
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Deal.class.getName() + " where id=" + id);
        Deal deal = (Deal)q.getSingleResult();
        return deal;
    }
    
    /**
     * Insert a deal to datastore.
     * @param deal entity to insert
     */
    public void insert(Deal deal) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            if (!isExist(deal)) {
                em.persist(deal);
            }
            em.close();
        }
    }
    
    /**
     * Insert a deal to datastore
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
     * Edit deal
     * @param deal
     */
    public void update(Deal deal) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Deal innerDeal = em.find(Deal.class, deal.getId());
            innerDeal.setTitle(deal.getTitle());
            //innerDeal.setLink("/viewdeal.app?id=" + GeneralUtil.ReplaceNotation(GeneralUtil.RemoveSign4VietNameseString(title), " ", "-"));
            innerDeal.setDescription(deal.getDescription());
            innerDeal.setAddress(deal.getAddress());
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
        } finally {         // Close connection
            em.close();
        }
    }
    
    /** 
     * Check if a deal exist.
     * @param deal object need to check
     * @return True if deal has a link exist in data store and deal
     * has status not DELETED, false otherwise.
     */
    public boolean isExist(Deal deal) {
        for (Deal item : this.listDeals()) {
            if (deal.getLink().equals(item.getLink())
                    && (deal.getStatus() != Status.DELETED.ordinal())) {
                return true;
            }
        }
        return false;        
    }
}
