/**
 * Deal.java
 * 21/6/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.model;

/**
 * Class contain data about Deal and Address.
 * @version 1.0 21/6/2012
 * @author Khoa
 */
public class DealDetail {
    private Long id;
    /** Title of deal. */
    private String title;
    /** Url of deal. */
    private String link;
    /** Longitude. */
    private double longitude;
    /** Latitude. */
    private double latitude;
    /**
     * Constructor.
     */
    public DealDetail(){
        
    }
    /**
     * Constructor.
     * @param id
     * @param title
     * @param link
     * @param longitude
     * @param latitude
     */
    public DealDetail(Long id, String title, String link, double longitude, double latitude){
        this.id = id;
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
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
     * Get value of title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the value for title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get value of link.
     * @return the link
     */
    public String getLink() {
        return link;
    }
    /**
     * Set the value for link.
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
