package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.List;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

/**
 * 
 * @author Nixforest
 *
 * A Bussiness logic object class for Deal table
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
     * 
     * Insert a deal.
     * @param deal object to insert into database
     * @throws Exception
     */
    public void insert(Deal deal) throws Exception{
        DealDAO.INSTANCE.insert(deal.getTitle(), deal.getDescription(), deal.getAddress(), deal.getLink(), deal.getImageLink(), deal.getPrice(), deal.getBasicPrice(), deal.getUnitPrice(), deal.getSave(), deal.getNumberBuyer(), deal.getEndTime(), deal.isVoucher());        
    }
    /**
     * Delete a deal.
     * @param id object's id
     * @throws Exception
     */
    public void delete(long id) {
        DealDAO.INSTANCE.deleteByChangeStatus(id);
    }
    /**
     * 
     * Restore a deal.
     * @param id object's id
     * @throws Exception
     */
    public void restore(long id) {
        DealDAO.INSTANCE.restoreChangeStatus(id);
    }
    /**
    * Get value of listAllDeals.
    * @return the listAllDeals
    */
    public List<Deal> getListAllDeals() {
        return DealDAO.INSTANCE.listDeals();
    }
}
