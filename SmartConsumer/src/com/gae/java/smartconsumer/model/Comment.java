/**
 * Comment.java
 * 8 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Comment class.
 * @version v1.0 08/01/2013
 * @author NguyenPT
 */
@Entity(name = "Comment")
public class Comment {
    /**
     * Id - Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CommentId")
    private Long id;
    /**
     * Deal id.
     */
    private Long dealId;
    /**
     * Username of user comment.
     */
    private String username;
    /**
     * Content of comment.
     */
    private String content;
    /**
     * Commit time.
     */
    private java.util.Date commitTime;
    /**
     * Empty constructor.
     */
    public Comment() {
    }
    /**
     * Constructor of Comment class.
     * @param dealId Id of deal
     * @param username Username has commented
     * @param content Content of comment
     */
    public Comment(Long dealId, String username, String content) {
        this.dealId = dealId;
        this.username = username;
        this.content = content;
        this.setCommitTime(java.util.Calendar.getInstance().getTime());
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
    * Get value of username.
    * @return the username
    */
    public String getUsername() {
        return username;
    }
    /**
     * Set the value for username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
    * Get value of content.
    * @return the content
    */
    public String getContent() {
        return content;
    }
    /**
     * Set the value for content.
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
    * Get value of commitTime.
    * @return the commitTime
    */
    public java.util.Date getCommitTime() {
        return commitTime;
    }
    /**
     * Set the value for commitTime.
     * @param commitTime the commitTime to set
     */
    public void setCommitTime(java.util.Date commitTime) {
        this.commitTime = commitTime;
    }
}
