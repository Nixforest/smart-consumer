/**
 * DealBLO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.model.DealSortByEndTime;
import com.gae.java.smartconsumer.model.DealSortById;
import com.gae.java.smartconsumer.model.DealSortByUpdateDate;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.GlobalVariable;
import com.gae.java.smartconsumer.util.Status;

/**
 * Business logic class for Deal object.
 * @version 2.0 03/06/2012 - Update - NguyenPT
 * @version 2.0 13/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum DealBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * List all of Deal in data store.
     * @return List of all Deal
     */
    public List<Deal> getListAllDeals() {
        return DealDAO.INSTANCE.getListAllDeals();
    }
    /**
     * List all of Deal are selling in data store.
     * @return List<Deal>
     */
    public List<Deal> getListAllDealsSelling() {
        return DealDAO.INSTANCE.getListActiveDeals();
    }
    /**
     * Limit get deal.
     * @param limit limit
     * @return List<Deal>
     */
    public List<Deal> listDealsLimit(int limit) {
        /*List<Deal> listDealsSellingSortByUpdateDate = this.listDealsSellingSortByUpdateDate();
        List<Deal> listDeal = new ArrayList<Deal>();
        for (Deal item : listDealsSellingSortByUpdateDate) {
            listDeal.add(item);
        }
        // Reverse this list
        Collections.reverse(listDeal);
        return listDeal.subList(0, limit);*/
        int page = limit / GlobalVariable.DEAL_PER_PAGE_HOME;
        if (page <= 0) {
            page = 1;
        }
        return this.listDealsSellingSortByUpdateDate(page);
    }
    /**
     * Method get all deals sort by updateDate property.
     * @return List of deals sort by updateDate property.
     */
    public List<Deal> listDealsSortByUpdateDate() {
        List<Deal> listAllDeals = DealDAO.INSTANCE.getListAllDeals();
        Collections.sort(listAllDeals, new DealSortByUpdateDate());
        return listAllDeals;
    }
    /**
     * Method get some deals sort by updateDate property.
     * @param page page number
     * @return List of deals sort by updateDate property.
     */
    public List<Deal> listDealsSortByUpdateDate(int page) {
        List<Deal> result = DealDAO.INSTANCE.getListAllDeals(page);
        //Collections.sort(result, new DealSortByUpdateDate());
        // List count < number of deal need
        /*if (result.size() < page * GlobalVariable.DEAL_PER_PAGE_DEALMANAGER) {
            // List count < number of deal in a page
            if (result.size() < GlobalVariable.DEAL_PER_PAGE_DEALMANAGER) {
                return result;
            } else {
                return result.subList(0, GlobalVariable.DEAL_PER_PAGE_DEALMANAGER);
            }
        } else {
            return result.subList((page - 1) * GlobalVariable.DEAL_PER_PAGE_DEALMANAGER,
                page * GlobalVariable.DEAL_PER_PAGE_DEALMANAGER);
        }*/
        return result;
    }
    /**
     * Method get all deals sort by EndTime property.
     * @return List of deals sort by EndTime property.
     */
    public List<Deal> listDealsSortByEndTime() {
        List<Deal> listAllDeals = DealDAO.INSTANCE.getListAllDeals();
        Collections.sort(listAllDeals, new DealSortByEndTime());
        return listAllDeals;
    }
    /**
     * Method get all deals sort by Id property.
     * @return List of deals sort by Id property.
     */
    public List<Deal> listDealsSortById() {
        List<Deal> listAllDeals = DealDAO.INSTANCE.getListAllDeals();
        Collections.sort(listAllDeals, new DealSortById());
        return listAllDeals;
    }
    /**
     * Get Deal by Id.
     * @param id Id of Deal
     * @return Deal object has Id match
     */
    public Deal getDealById(Long id) {
        List<Deal> listAllDeals = DealDAO.INSTANCE.getListAllDeals();
        for (Deal deal : listAllDeals) {
            if (deal.getId().equals(id)) {
                return deal;
            }
        }
        return null;
    }
    /**
     * Get max Id in Deal data.
     * @return Max Id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        List<Deal> listDealsSortById = this.listDealsSortById();
        if (listDealsSortById.size() == 0) {
            return (long) 0;
        } else {
            return listDealsSortById.get(listDealsSortById.size() - 1).getId();
        }
    }
    /**
     * Method get all deals has Status = SELLING.
     * @return List of Deals sort by update date
     */
    public List<Deal> listDealsSellingSortByUpdateDate() {
        List<Deal> result = DealDAO.INSTANCE.getListActiveDeals();
        Collections.sort(result, new DealSortByUpdateDate());
        return result;
    }
    /**
     * Method get some deals has Status = SELLING.
     * @param page number of current page
     * @return List of Deals sort by update date
     */
    public List<Deal> listDealsSellingSortByUpdateDate(int page) {
        List<Deal> result = DealDAO.INSTANCE.getListActiveDeals(page);
        //Collections.sort(result, new DealSortByUpdateDate());
        // List count < number of deal need
        if (result.size() < page * GlobalVariable.DEAL_PER_PAGE_HOME) {
            // List count < number of deal in a page
            if (result.size() < GlobalVariable.DEAL_PER_PAGE_HOME) {
                return result;
            } else {
                return result.subList(0, GlobalVariable.DEAL_PER_PAGE_HOME);
            }
        } else {
            return result.subList((page - 1) * GlobalVariable.DEAL_PER_PAGE_HOME,
                page * GlobalVariable.DEAL_PER_PAGE_HOME);
        }
    }
    /**
     * Get count of all deals has Status = SELLING.
     * @return Count of all deals has Status = SELLING
     */
    public int getCountOfActiveDeal() {
        return DealDAO.INSTANCE.getCountOfActiveDeal();
    }
    /**
     * Get count of all deals in data store.
     * @return Count of all deals in data store.
     */
    public int getCountOfAllDeals() {
        return DealDAO.INSTANCE.getCountOfAllDeals();
    }
    /**
     * Method get all deals has Status = SELLING.
     * @return List of Deals sort by EndTime
     */
    public List<Deal> listDealsSellingSortByEndTime() {
        List<Deal> result = DealDAO.INSTANCE.getListActiveDeals();
        Collections.sort(result, new DealSortByEndTime());
        return result;
    }
    /**
     * Get a deal by title.
     * @param title title of deal (formatted)
     * @return Deal object
     */
    public Deal getDealByTitle(String title) {
        for (Deal item : DealDAO.INSTANCE.getListActiveDeals()) {
            // Remove sign for Vietnamese string
            String innerTitle = GeneralUtil.removeSign4VietNameseString(item.getTitle());
            // Replace white space in string by "-"
            innerTitle = GeneralUtil.replaceNotation(innerTitle, " ", "-");
            if (innerTitle.equals(title)) {
                return item;
            }
        }
        return null;
    }
    /**
     * Insert a deal.
     * @param deal object to insert into database
     * @return 0 if Deal is exist, Deal's id otherwise
     * @throws Exception A exception threw when Deal's properties is invalid
     */
    public Long insert(Deal deal) throws Exception {
        if (deal.getTitle().isEmpty()) {
            throw new Exception("Title can not empty");
        }
        if (deal.getLink().isEmpty()) {
            throw new Exception("Link can not empty");
        }
        if (deal.getImageLink().isEmpty()) {
            deal.setImageLink("default.jpg");
        }
        if (deal.getPrice() < 0) {
            throw new Exception("Price can not less than zero");
        }
        if (deal.getBasicPrice() < 0) {
            throw new Exception("Basic price can not less than zero");
        }
        if (deal.getUnitPrice().isEmpty()) {
            throw new Exception("Unit price can not empty");
        }
        // Set Save
        float save = Math.round((float) ((deal.getBasicPrice() - deal.getPrice()) / deal.getBasicPrice() * 100.0));
        deal.setSave(save);
        if (deal.getNumberBuyer() < 0) {
            throw new Exception("Number buyer can not less than zero");
        }
        deal.setId(getMaxId() + 1);
        if (DealDAO.INSTANCE.isExistInListAllDeals(deal)) {
            return (long) 0;
        }
        if (this.isExist(deal)) {
            throw new Exception("Deal was exist!");
        }
        DealDAO.INSTANCE.insert(deal);
        return deal.getId();
    }
    /**
     * Insert list insert deals into data store.
     */
    public void insertIntoDataStore() {
        DealDAO.INSTANCE.insertIntoDatastore();
    }
    /**
     * Delete a deal.
     * @param id object's id
     * @throws Exception Throw exception when Id does not exist
     */
    public void delete(long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist!");
        }
        DealDAO.INSTANCE.changeStatus(id, Status.DELETED.ordinal());
    }
    /**
     * Delete a deal physical.
     * @param id object's id
     * @throws Exception Throw exception when Id does not exist
     */
    public void remove(long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist!");
        }
        // Delete address detail relate
        for (AddressDetail detail : AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(id)) {
            AddressDetailBLO.INSTANCE.delete(detail.getId());
        }
        // Detele deal
        DealDAO.INSTANCE.delete(id);
    }
    /**
     * Restore a deal.
     * @param id object's id
     * @throws Exception Throw exception when Id does not exist
     */
    public void restore(long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist!");
        }
        DealDAO.INSTANCE.changeStatus(id, Status.WAITTOCHECK.ordinal());
    }

    /**
     * Method change status of deal.
     * @param id id of deal
     * @param changeToStatus status that change to
     * @throws Exception Exception threw when id is invalid
     */
    public void changeStatus(long id, int changeToStatus) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist!");
        }
        DealDAO.INSTANCE.changeStatus(id, changeToStatus);
    }
    /**
     * Method change status of deal.
     * @param deal Deal to change status
     * @param changeToStatus status that change to
     * @throws Exception Exception threw when id is invalid
     */
    public void changeStatus(Deal deal, int changeToStatus) throws Exception {
        if (!isIdExist(deal.getId())) {
            throw new Exception("Id does not exist!");
        }
        DealDAO.INSTANCE.changeStatus(deal, changeToStatus);
    }
    /**
     * Method check if Id exist.
     * @param id id need to check
     * @return True if Id exist, false otherwise.
     */
    public boolean isIdExist(long id) {
        boolean result = false;
        for (Deal item : DealDAO.INSTANCE.getListAllDeals()) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return result;
    }
    /**
     * Check if a deal exist.
     * @param deal object need to check
     * @return True if deal has a link exist in db and deal has status not DELETED, false otherwise.
     */
    public boolean isExist(Deal deal) {
        return DealDAO.INSTANCE.isExistInListAllDeals(deal);
    }

    /**
     * Update method.
     * @param deal deal to update
     * @throws Exception A exception threw when Deal's properties is invalid
     */
    public void update(Deal deal) throws Exception {
        if (!isIdExist(deal.getId())) {
            throw new Exception("Id is invalid");
        }
        if (deal.getTitle().isEmpty()) {
            throw new Exception("Title can not empty");
        }
        if (deal.getImageLink().isEmpty()) {
            deal.setImageLink("default.jpg");
        }
        if (deal.getPrice() < 0) {
            throw new Exception("Price can not less than zero");
        }
        if (deal.getBasicPrice() < 0) {
            throw new Exception("Basic price can not less than zero");
        }
        if (deal.getUnitPrice().isEmpty()) {
            throw new Exception("Unit price can not empty");
        }
        if (deal.getNumberBuyer() < 0) {
            throw new Exception("Number buyer can not less than zero");
        }
        deal.setLink("/viewdeal.app?id="
                + GeneralUtil.replaceNotation(GeneralUtil.removeSign4VietNameseString(deal.getTitle()), " ", "-"));
        deal.setSave((float) ((deal.getBasicPrice() - deal.getPrice()) / deal.getBasicPrice() * 100));
        // deal.setStatus(Status.WAITTOCHECK.ordinal());
        deal.setUpdateDate(java.util.Calendar.getInstance().getTime());

        DealDAO.INSTANCE.update(deal);
    }
    /**
     * Search Deal by Price.
     * @param priceFrom Price from
     * @param priceTo Price to
     * @return list Deal
     */
    public List<Deal> searchByPrice(double priceFrom, double priceTo) {
        List<Deal> lst = new ArrayList<Deal>();
        List<Deal> lstActiveDeal = DealDAO.INSTANCE.getListActiveDeals();
        for (Deal deal : lstActiveDeal) {
            if (deal.getPrice() <= priceTo && deal.getPrice() >= priceFrom) {
                lst.add(deal);
            }
        }
        return lst;
    }
}
