/**
 * BillBLO.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.blo;

import java.util.List;

import com.gae.java.smartconsumer.dao.BillDetailDAO;
import com.gae.java.smartconsumer.model.BillDetail;

/**
 * Business logic class for Bill Detail object
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public enum BillDetailBLO {
    INSTANCE;
    
    /**
     * Method get all bill details in data store.
     * @return List of BillDetails
     */
    public List<BillDetail> getAllBillDetails() {
        return BillDetailDAO.INSTANCE.listBillDetails();
    }
    
    /**
     * Get bill detail by Id.
     * @param id Id of bill detail
     * @return object has Id match parameter
     */
    public BillDetail getBillDetailById(Long id) {
        return BillDetailDAO.INSTANCE.getBillDetailById(id);
    }
    
    /**
     * Insert.
     * @param billDetail object to insert
     */
    public void insert(BillDetail billDetail) {
        billDetail.setId(BillDetailDAO.INSTANCE.getMaxId() + 1);
        BillDetailDAO.INSTANCE.insert(billDetail);
    }
}
