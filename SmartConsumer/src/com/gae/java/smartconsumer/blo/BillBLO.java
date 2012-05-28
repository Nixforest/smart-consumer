/**
 * BillBLO.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.Calendar;
import java.util.List;

import com.gae.java.smartconsumer.dao.BillDAO;
import com.gae.java.smartconsumer.model.Bill;

/**
 * Business logic class for Bill object
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum BillBLO {
    INSTANCE;
    
    /** List of all bill */
    private List<Bill> listAllBills = BillDAO.INSTANCE.listBills();
    
    /**
     * Method get all bills in data store.
     * @return List of Bills
     */
    public List<Bill> getAllBills() {
        return BillDAO.INSTANCE.listBills();
    }
    
    /**
     * Method get a bill by Id.
     * @param id Id of Bill
     * @return Bill object has Id match
     * @throws Exception A exception throw if Id does not exist in data store
     */
    public Bill getBillById(Long id) throws Exception {
        if (!isBillExist(id)) {
            throw new Exception("Bill Id does not exist");
        }
        return BillDAO.INSTANCE.getBillById(id);
    }
    
    /**
     * Insert a bill.
     * @param bill bill to insert
     * @throws Exception A exception threw when Bill's properties is invalid
     */
    public Long insert(Bill bill) throws Exception {
        if (bill.getCustomerName().isEmpty()) {
            throw new Exception("Customer's name can not empty");
        }
        if (bill.getCustomerEmail().isEmpty()) {
            throw new Exception("Customer's email can not empty");
        }
        if (bill.getCardNumber().isEmpty()) {
            throw new Exception("Card number can not empty");
        }
        if (bill.getHolderName().isEmpty()) {
            throw new Exception("Holder's name can not empty");
        }
        if (bill.getExpirationDate().before(Calendar.getInstance().getTime())) {
            throw new Exception("Expiration date is invalid");
        }
        bill.setId(BillDAO.INSTANCE.getMaxId() + 1);
        BillDAO.INSTANCE.insert(bill);
        return bill.getId();
    }
    
    /**
     * Update a bill.
     * @param bill bill to insert
     * @throws Exception A exception threw when Bill's properties is invalid
     */
    public void update(Bill bill) throws Exception {
        if (!isBillExist(bill.getId())) {
            throw new Exception("Bill Id does not exist");
        }
        if (bill.getCustomerName().isEmpty()) {
            throw new Exception("Customer's name can not empty");
        }
        if (bill.getCustomerEmail().isEmpty()) {
            throw new Exception("Customer's email can not empty");
        }
        if (bill.getCardNumber().isEmpty()) {
            throw new Exception("Card number can not empty");
        }
        if (bill.getHolderName().isEmpty()) {
            throw new Exception("Holder's name can not empty");
        }
        if (bill.getExpirationDate().before(Calendar.getInstance().getTime())) {
            throw new Exception("Expiration date is invalid");
        }
        BillDAO.INSTANCE.update(bill);
    }
    
    /**
     * Delete a bill.
     * @param id object's id
     * @throws Exception Throw exception when Id does not exist
     */
    public void delete(Long id) throws Exception {
        if (!isBillExist(id)) {
            throw new Exception("Bill Id does not exist");
        }
        BillDAO.INSTANCE.delete(id);
    }
    
    /**
     * Method check if Id exist.
     * @param id id need to check
     * @return True if Id exist, false otherwise.
     */
    public boolean isBillExist(Long id) {
        for (Bill item : listAllBills) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
