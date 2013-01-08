/**
 * Category.java
 * 24 Dec 2012
 * SmartConsumer project.
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class describe "Category" object, contain data about Category.
 * @version 1.0 24/12/2012
 * @author NguyenPT
 */
@Entity(name = "Category")
public class Category {
    /**
     * Id - Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CategoryID")
    private Long id;
    /**
     * Name of category.
     */
    private String name;
    /**
     * Description of category.
     */
    private String description;
    /**
     * Parent Id of category.
     */
    private Long parentId;
    /**
     * Link of category.
     */
    private String link;
    /**
     * Empty constructor.
     */
    public Category() {
    }
    /**
     * Constructor has 4 parameters.
     * @param name Name of category
     * @param description Description of category
     * @param parentId Parent Id of category
     * @param link Link of category
     */
    public Category(String name, String description, Long parentId, String link) {
        this.setName(name);
        this.setDescription(description);
        this.setParentId(parentId);
        this.setLink(link);
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
    * Get value of name.
    * @return the name
    */
    public String getName() {
        return name;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
    /**
    * Get value of parentId.
    * @return the parentId
    */
    public Long getParentId() {
        return parentId;
    }
    /**
     * Set the value for parentId.
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}
