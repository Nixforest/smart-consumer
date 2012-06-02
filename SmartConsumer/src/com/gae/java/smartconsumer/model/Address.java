/**
 * Address.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

/**
 * Class describe "Address" object, contain data about address.
 * @version 2.0 2/6/2012
 * @author Nixforest
 */
@Entity(name = "Address")
public class Address {
    /** Id - Primary key. */
    @Id
    private Long id;

    /** Full address. */
    private String fullAddress;

    /** Longitude. */
    private double longitude;

    /** Latitude. */
    private double latitude;

    /** Description. */
    private String description;
    /**
     * Constructor.
     * @param fullAddress Full address
     * @param longitude Longitude
     * @param latitude Latitude
     * @param description Description
     */
    public Address(String fullAddress, double longitude, double latitude, String description) {
        this.setFullAddress(fullAddress);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setDescription(description);
    }
    
    /**
     * Constructor of Address class.
     * @param JSONObject
    */
    public Address(JSONObject jsonObject) throws JSONException{
        if(jsonObject.has("id")){
            id = jsonObject.getLong("id");
        }
        if(jsonObject.has("fullAddress")){
            fullAddress = jsonObject.getString("fullAddress");
        }
        if(jsonObject.has("longitude")){
            longitude = jsonObject.getDouble("longitude");
        }
        if(jsonObject.has("latitude")){
            latitude = jsonObject.getDouble("latitude");
        }
        if(jsonObject.has("description")){
            description = jsonObject.getString("description");
        }
    }
    
    /**
     * get Address
     * @return JSONObject 
     */
    public JSONObject toJSONObject() throws JSONException{
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("fullAddress", fullAddress);
        result.put("longitude", longitude);
        result.put("latitude", latitude);
        result.put("description", description);
        
        return result;
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

    /**
    * Get value of description.
    * @return the description
    */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
