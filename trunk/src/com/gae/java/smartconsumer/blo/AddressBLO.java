/**
 * AddressBLO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.List;

import com.gae.java.smartconsumer.dao.AddressDAO;
import com.gae.java.smartconsumer.model.Address;

/**
 * Business logic class for Address object.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum AddressBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * Get all addresses.
     * @return List of all addresses
     */
    public List<Address> getAllAddresses() {
        return AddressDAO.INSTANCE.listAddresses();
    }
    /**
     * Get all addresses sort by Id property.
     * @return List of all addresses sort by Id property.
     */
    public List<Address> getAllAddressesSortById() {
        return AddressDAO.INSTANCE.listAddressesSortById();
    }
    /**
     * Get address by Id.
     * @param id id of address
     * @return Address has id match with parameter
     * @throws Exception Exception happen id does not exist in data store
     */
    public Address getAddressById(Long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist");
        }
        return AddressDAO.INSTANCE.getAddressById(id);
    }
    /**
     * Insert an address .
     * @param address Address to insert
     * @return Id of address
     * @throws Exception Exception happen when full address property is empty
     */
    public Long insert(Address address) throws Exception {
        // Set new Id
        address.setId(AddressDAO.INSTANCE.getMaxId() + 1);
        // Check full address can not be empty
        if (address.getFullAddress().isEmpty()) {
            throw new Exception("Address can not empty");
        }
        // Check address is exist
        Long addressExist = isAddressExist(address.getFullAddress());
        if (addressExist != 0) {
            return addressExist;
        }
        AddressDAO.INSTANCE.insert(address);
        return address.getId();
    }
    /**
     * Update an address.
     * @param address Address to update
     * @throws Exception Exception happen when full address property is empty
     * or Id does not exist.
     */
    public void update(Address address) throws Exception {
        if (!isIdExist(address.getId())) {
            throw new Exception("Id does not exist");
        }
        if (address.getFullAddress().isEmpty()) {
            throw new Exception("Address can not empty");
        }
        AddressDAO.INSTANCE.update(address);
    }
    /**
     * Delete method.
     * @param id Id of address
     * @throws Exception Exception happen id does not exist in data store
     * or Address's Id is exist in AddressDetail data
     */
    public void delete(Long id) throws Exception {
        if (!isIdExist(id)) {
            throw new Exception("Id does not exist");
        }
        if (AddressDetailBLO.INSTANCE.isAddressIdExist(id)) {
            throw new Exception("Address can not delete because it is using by some Deal");
        }
        AddressDAO.INSTANCE.delete(id);
    }
    /**
     * Check if id is exist.
     * @param id id to check
     * @return True if Id is exist, false otherwise.
     */
    public boolean isIdExist(Long id) {
        for (Address item : getAllAddresses()) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check if address is exist.
     * @param address Address to check
     * @return Id of item which Address match, 0 otherwise.
     */
    public Long isAddressExist(String address) {
        for (Address item : getAllAddresses()) {
            if (item.getFullAddress().equals(address)) {
                return item.getId();
            }
        }
        return (long) 0;
    }
}
