/**
 * DealBLO.java
 * 2/6/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.blo;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.google.appengine.repackaged.org.json.JSONObject;

/**
 * Business logic mobile class for Deal object.
 * @version 1.0 2/6/2012
 * @author GENIUS
 */
public class DealBLO {
    /**
     * Get a deal by Id.
     * @param id id of Deal
     * @return JSONObject
     */
    public JSONObject getDealById(Long id) {
        
        return DealDAO.INSTANCE.getDealById(id);
    }
}
