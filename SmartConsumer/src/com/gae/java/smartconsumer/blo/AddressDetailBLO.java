/**
 * AddressDetailBLO.java
 * 29/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.gae.java.smartconsumer.dao.AddressDetailDAO;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.AddressDetailSortById;

/**
 * Business logic class for AddressDetail object.
 * @version 1.0 29/05/2012
 * @version 2.0 15/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum AddressDetailBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * List all Address Detail in data store.
     * @return List all AddressDetail
     */
    public List<AddressDetail> getAllAddressDetails() {
        return AddressDetailDAO.INSTANCE.getListAllAddressDetails();
    }
    /**
     * List all Address Detail sort by Id property.
     * @return List all Address Detail sort by Id property.
     */
    public List<AddressDetail> getAllAddressDetailsSortById() {
        List<AddressDetail> listAllAddressDetails = AddressDetailDAO.INSTANCE.getListAllAddressDetails();
        Collections.sort(listAllAddressDetails, new AddressDetailSortById());
        return listAllAddressDetails;
    }
    /**
     * Get all AddressDetail of a Deal by Deal's Id.
     * @param dealId Deal's Id
     * @return List AddressDetails
     */
    public List<AddressDetail> getAddressDetailsByDealId(Long dealId) {
        List<AddressDetail> result = new ArrayList<AddressDetail>();
        for (AddressDetail item : AddressDetailDAO.INSTANCE.getListAllAddressDetails()) {
            if (item.getDealId().equals(dealId)) {
                result.add(item);
            }
        }
        return result;
    }
    /**
     * Get AddressDetail by Id.
     * @param id Id of AddressDetail
     * @return AddressDetail has Id match with parameter
     */
    public AddressDetail getAddressDetailById(Long id) {
        for (AddressDetail detail : AddressDetailDAO.INSTANCE.getListAllAddressDetails()) {
            if (detail.getId().equals(id)) {
                return detail;
            }
        }
        return null;
    }
    /**
     * Get max Id in Deal data.
     * @return Max Id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        List<AddressDetail> listAllAddressDetails = this.getAllAddressDetailsSortById();
        if (listAllAddressDetails.size() == 0) {
            return (long) 0;
        } else {
            return listAllAddressDetails.get(listAllAddressDetails.size() - 1).getId();
        }
    }
    /**
     * Insert an AddressDetail.
     * @param detail AddressDetail object
     * @return Id of object has just inserted
     * @throws Exception Exception happen when Id of deal or
     * address does not exist
     */
    public Long insert(AddressDetail detail) throws Exception {
        detail.setId(this.getMaxId() + 1);
        AddressDetailDAO.INSTANCE.insert(detail);
        return detail.getId();
    }
    /**
     * Insert list insert address details into data store.
     */
    public void insertIntoDataStore() {
        AddressDetailDAO.INSTANCE.insertIntoDatastore();
    }
    /**
     * Delete method.
     * @param id Id of AddressDetail
     * @throws Exception Id does not exist
     */
    public void delete(Long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist");
        }
        AddressDetailDAO.INSTANCE.delete(id);
    }
    /**
     * Method check if Id exist.
     * @param id id need to check
     * @return True if Id exist, false otherwise.
     */
    public boolean isIdExist(long id) {
        for (AddressDetail item : getAllAddressDetails()) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method check if Deal's Id exist.
     * @param dealId DealId need to check
     * @return True if DealId exist, false otherwise.
     */
    public boolean isDealIdExist(Long dealId) {
        for (AddressDetail item : getAllAddressDetails()) {
            if (item.getDealId().equals(dealId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method check if Address's Id exist.
     * @param addressId AddressId need to check
     * @return if AddressId exist, false otherwise.
     */
    public boolean isAddressIdExist(Long addressId) {
        for (AddressDetail item : getAllAddressDetails()) {
            if (item.getAddressId().equals(addressId)) {
                return true;
            }
        }
        return false;
    }
}
