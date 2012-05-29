/**
 * AddressDetailBLO.java
 * 29/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.List;

import com.gae.java.smartconsumer.dao.AddressDetailDAO;
import com.gae.java.smartconsumer.dao.DealDAO;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Business logic class for AddressDetail object.
 * @version 1.0 29/5/2012
 * @author Nixforest
 */
public enum AddressDetailBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * List all Address Detail in data store.
     * @return
     */
    public List<AddressDetail> getAllAddressDetails() {
        return AddressDetailDAO.INSTANCE.listAddressDetails();
    }
    /**
     * List all Address Detail sort by Id property.
     * @return List all Address Detail sort by Id property.
     */
    public List<AddressDetail> getAllAddressDetailsSortById() {
        return AddressDetailDAO.INSTANCE.listAddressDetailsSortById();
    }
    /**
     * Get AddressDetail by Id.
     * @param id Id of AddressDetail
     * @return AddressDetail has Id match with parameter
     * @throws Exception Exception threw when id is invalid
     */
    public AddressDetail getAddressDetailById(Long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist");
        }
        return AddressDetailDAO.INSTANCE.getAddressDetailById(id);
    }
    /**
     * Insert an AddressDetail.
     * @param detail AddressDetail object
     * @return Id of object has just inserted
     * @throws Exception Exception happen when Id of deal or
     * address does not exist
     */
    public Long insert(AddressDetail detail) throws Exception {
        detail.setId(AddressDetailDAO.INSTANCE.getMaxId() + 1);
        if (!DealBLO.INSTANCE.isIdExist(detail.getDealId())) {
            throw new Exception("Id of deal does not exist");
        }
        if (!AddressBLO.INSTANCE.isIdExist(detail.getAddressId())) {
            throw new Exception("Id of address does not exist");
        }
        AddressDetailDAO.INSTANCE.insert(detail);
        return detail.getId();
    }
    /**
     * Update method.
     * @param detail AddressDetail
     * @throws Exception Exception happen when Id of deal or
     * address does not exist or Id does not exist
     */
    public void update(AddressDetail detail) throws Exception {
        if (!isIdExist(detail.getId())) {
            throw new Exception("Id does not exist");
        }
        if (!DealBLO.INSTANCE.isIdExist(detail.getDealId())) {
            throw new Exception("Id of deal does not exist");
        }
        if (!AddressBLO.INSTANCE.isIdExist(detail.getAddressId())) {
            throw new Exception("Id of address does not exist");
        }
        AddressDetailDAO.INSTANCE.update(detail);
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
     * 
     * [Give the description for method].
     * @param addressId
     * @return
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
