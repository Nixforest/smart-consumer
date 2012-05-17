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
package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.Status;

/**
 * @author Nixforest
 *
 */
public enum DealBLO {
    INSTANCE;
    // List all deals property
    private List<Deal> listAllDeals = DealDAO.INSTANCE.listDeals();
    /**
     * 
     * Method get all deals has Status = SELLING.
     * @return List of Deals sort by update date
     */    
    public List<Deal> listDealsSellingSortByUpdateDate() {
        List<Deal> result = new ArrayList<Deal>();
        for (Deal item : DealDAO.INSTANCE.listDealsSortByUpdateDate()) {
            if (item.getStatus() == Status.SELLING.ordinal()) {
                result.add(item);
            }
        }
        return result;
    }    
    /**
     * 
     * Method get all deals has Status = SELLING.
     * @return List of Deals sort by EndTime
     */    
    public List<Deal> listDealsSellingSortByEndTime() {
        List<Deal> result = new ArrayList<Deal>();
        for (Deal item : DealDAO.INSTANCE.listDealsSortByEndTime()) {
            if (item.getStatus() == Status.SELLING.ordinal()) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Get a deal by Id.
     * @param id
     * @return
     */
    public Deal getOne(Long id){
        return DealDAO.INSTANCE.getOne(id);
    }

    /**
     * Get a deal by title.
     * @param title title of deal
     * @return Deal
     */
    public Deal getDealByTitle(String title){
        for(Deal item : DealDAO.INSTANCE.listDeals()){
            if(GeneralUtil.ReplaceNotation(GeneralUtil.RemoveSign4VietNameseString(item.getTitle()), " ", "-").equals(title)){
                return item;
            }
        }
        return null;
    }
    /**
     * 
     * Method get all deals has been created by user.
     * @return List of Deals
     */ 
    public List<Deal> listDealByCreate(){
        List<Deal> result = new ArrayList<Deal>();
        for(Deal item : DealDAO.INSTANCE.listDeals()){
            if(item.getLink().substring(0, 9).equals("/viewdeal")){
                result.add(item);
            }
        }
        return result;
    }
    /**
     * 
     * Insert a deal.
     * @param deal object to insert into database
     * @throws Exception
     */
    public Long insert(Deal deal) throws Exception{
        return DealDAO.INSTANCE.insert(deal);        
    }
    /**
     * Delete a deal.
     * @param id object's id
     * @throws Exception
     */
    public void delete(long id) throws Exception {
        if (isIdExist(id)) {
            DealDAO.INSTANCE.deleteByChangeStatus(id);
        } else {
            throw new Exception("Id does not exist!");
        }
    }
    /**
     * 
     * Restore a deal.
     * @param id object's id
     * @throws Exception
     */
    public void restore(long id) throws Exception {
        if (isIdExist(id)) {
        DealDAO.INSTANCE.restoreChangeStatus(id);
        } else {
            throw new Exception("Id does not exist!");
        }
    }
    /**
     * Method change status of deal.
     * @param id id of deal
     * @param changeToStatus status that change to
     * @throws Exception Exception throw when id is invalid
     */
    public void changeStatus(long id, int changeToStatus) throws Exception {
        if (isIdExist(id)) {
            DealDAO.INSTANCE.changeStatus(id, changeToStatus);
        } else {
            throw new Exception("Id does not exist!");
        }
    }
    /**
    * Get value of listAllDeals.
    * @return the listAllDeals
    */
    public List<Deal> getListAllDeals() {
        return DealDAO.INSTANCE.listDeals();
    }
    
    /**
     * Method check if Id exist.
     * @param id id need to check
     * @return True if Id exist, false otherwise.
     */
    public boolean isIdExist(long id) {
        boolean result = false;
        for (Deal item : this.listAllDeals) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return result;
    }
    
    public boolean isExist(Deal deal) {
        return DealDAO.INSTANCE.isExist(deal);
    }
    public void editDeal(long id, String title, String description, String address, String imageLink, Float price,
            Float basicPrice, String unitPrice, Boolean isVoucher, Date endTime){
        DealDAO.INSTANCE.editDeal(id, title, description, address, imageLink, price, basicPrice, unitPrice, isVoucher, endTime);
    }
}
