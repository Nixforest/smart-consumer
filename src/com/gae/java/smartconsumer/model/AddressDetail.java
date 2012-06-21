/**
 * AddressDetail.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class describe "AddressDetail" object, contain data about AddressDetail.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
@Entity(name = "AddressDetail")
public class AddressDetail {
    /** Id - Primary key. */
    @Id
    private Long id;
    /** Deal's Id. */
    private Long dealId;
    /** Address's Id. */
    private Long addressId;
    /**
     * Constructor.
     * @param dealId Id of deal
     * @param addressId Id of Address
     */
    public AddressDetail(Long dealId, Long addressId) {
        this.setDealId(dealId);
        this.setAddressId(addressId);
    }
    /**
    * Get value of id.
    * @return the id
    */
    public Long getId() {
        return id;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
    * Get value of dealId.
    * @return the dealId
    */
    public Long getDealId() {
        return dealId;
    }
    /**
     * Set the value for dealId.
     * @param dealId the dealId to set
     */
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }
    /**
    * Get value of addressId.
    * @return the addressId
    */
    public Long getAddressId() {
        return addressId;
    }
    /**
     * Set the value for addressId.
     * @param addressId the addressId to set
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
