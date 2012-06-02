/**
 * DealBLO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.List;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.Status;

/**
 * Business logic class for Deal object.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum DealBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * List all of Deal in data store.
     * @return List of all Deal
     */
    public List<Deal> getListAllDeals() {
        return DealDAO.INSTANCE.listDeals();
    }

    /**
     * Method get all deals has been created by user (not auto collect).
     * @return List of Deals
     */
    public List<Deal> listDealByCreate() {
        List<Deal> result = new ArrayList<Deal>();
        for (Deal item : DealDAO.INSTANCE.listDeals()) {
            if (item.getLink().substring(0, 9).equals("/viewdeal")) {
                result.add(item);
            }
        }
        return result;
    }
    /**
     * Method get all deals (selling) has been created by user (not auto collect).
     * @return List of Deals
     */
    public List<Deal> listDealSellingByCreate() {
        List<Deal> result = new ArrayList<Deal>();
        for (Deal item : listDealByCreate()) {
            if (item.getStatus() == Status.SELLING.ordinal()) {
                result.add(item);
            }
        }
        return result;
    }

    /**
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
     * @param id id of Deal
     * @return Deal object
     */
    public Deal getDealById(Long id) {
        return DealDAO.INSTANCE.getDealById(id);
    }

    /**
     * Get a deal by title.
     * @param title title of deal (formatted)
     * @return Deal object
     */
    public Deal getDealByTitle(String title) {
        for (Deal item : DealDAO.INSTANCE.listDeals()) {
            // Remove sign for vietnamese string
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
        if ((deal.getStatus() < Status.WAITTOCHECK.ordinal()) || (deal.getStatus() > Status.OUTOFTIME.ordinal())) {
            throw new Exception("Status is invalid");
        }
        deal.setId(DealDAO.INSTANCE.getMaxId() + 1);
        if (DealDAO.INSTANCE.isExist(deal)) {
            return (long) 0;
        }
        DealDAO.INSTANCE.insert(deal);
        return deal.getId();
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
     * Method check if Id exist.
     * @param id id need to check
     * @return True if Id exist, false otherwise.
     */
    public boolean isIdExist(long id) {
        boolean result = false;
        for (Deal item : DealDAO.INSTANCE.listDeals()) {
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
        return DealDAO.INSTANCE.isExist(deal);
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
}
