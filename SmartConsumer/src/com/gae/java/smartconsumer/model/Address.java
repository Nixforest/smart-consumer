/**
 * Address.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class describe "Address" object, contain data about address.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
@Entity(name = "Address")
public class Address {
    /** Id - Primary key.*/
    @Id
    private Long id;

    /** Full address. */
    private String fullAddress;

    /** Longitude. */
    private double longitude;

    /** Latitude. */
    private double latitude;

    /**
     * Constructor.
     * @param fullAddress Full address
     * @param longitude Longitude
     * @param latitude Latitude
     */
    public Address(String fullAddress, double longitude, double latitude) {
        this.setFullAddress(fullAddress);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
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
    * Get value of fullAddress.
    * @return the fullAddress
    */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * Set the value for fullAddress.
     * @param fullAddress the fullAddress to set
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
    * Get value of longitude.
    * @return the longitude
    */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value for longitude.
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
    * Get value of latitude.
    * @return the latitude
    */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value for latitude.
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
