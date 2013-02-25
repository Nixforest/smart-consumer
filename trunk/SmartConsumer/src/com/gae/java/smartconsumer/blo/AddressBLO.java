/**
 * AddressBLO.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.Collections;
import java.util.List;

import com.gae.java.smartconsumer.dao.AddressDAO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.AddressSortById;

/**
 * Business logic class for Address object.
 * @version 1.0 28/05/2012
 * @version 2.0 15/09/2012 - Update - NguyenPT
 * @author NguyenPT
 */
public enum AddressBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * Get all addresses.
     * @return List of all addresses
     */
    public List<Address> getAllAddresses() {
        return AddressDAO.INSTANCE.getListAllAddresses();
    }
    /**
     * Get all addresses sort by Id property.
     * @return List of all addresses sort by Id property.
     */
    public List<Address> getAllAddressesSortById() {
        List<Address> listAllAddresses = AddressDAO.INSTANCE.getListAllAddresses();
        Collections.sort(listAllAddresses, new AddressSortById());
        return listAllAddresses;
    }
    /**
     * Get max id in Address data.
     * @return Max id if it is exist, 0 otherwise
     */
    public Long getMaxId() {
        List<Address> listAddressesSortById = this.getAllAddressesSortById();
        if (listAddressesSortById.size() == 0) {
            return (long) 0;
        } else {
            return listAddressesSortById.get(listAddressesSortById.size() - 1).getId();
        }
    }
    /**
     * Get address by Id.
     * @param id id of address
     * @return Address has Id match parameter
     */
    public Address getAddressById(Long id) {
        List<Address> listAllAddresses = AddressDAO.INSTANCE.getListAllAddresses();
        for (Address address : listAllAddresses) {
            if (address.getId().equals(id)) {
                return address;
            }
        }
        return null;
    }
    /**
     * Insert an address .
     * @param address Address to insert
     * @return Id of address
     * @throws Exception Exception happen when full address property is empty
     */
    public Long insert(Address address) throws Exception {
        // Set new Id
        address.setId(getMaxId() + 1);
        // Check full address can not be empty
        if (address.getFullAddress().isEmpty()) {
            //throw new Exception("Address can not empty");
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
     * Insert list insert addresses into data store.
     */
    public void insertIntoDataStore() {
        AddressDAO.INSTANCE.insertIntoDatastore();
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
