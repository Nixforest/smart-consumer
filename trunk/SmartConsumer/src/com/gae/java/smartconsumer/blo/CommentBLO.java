/**
 * CommentBLO.java
 * 8 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.blo;

import java.util.List;

import com.gae.java.smartconsumer.dao.CommentDAO;
import com.gae.java.smartconsumer.model.Comment;

/**
 * Business logic class for Comment object.
 * @version v1.0 08/01/2013
 * @author NguyenPT
 */
public enum CommentBLO {
    /**
     * Instance of class.
     */
    INSTANCE;
    /**
     * Get comment object by Id.
     * @param id Id of comment
     * @return Comment object
     */
    public Comment getCommentById(long id) {
        return CommentDAO.INSTANCE.getCommentById(id);
    }
    /**
     * Get list comments object by dealID.
     * @param dealId Id of deal
     * @return List comments
     */
    public List<Comment> getCommentsByDealId(long dealId) {
        return CommentDAO.INSTANCE.getCommentsByDealId(dealId);
    }
    /**
     * Insert a comment object.
     * @param comment Comment object
     */
    public void insert(Comment comment) {
        comment.setId(CommentDAO.INSTANCE.getMaxId() + 1);
        CommentDAO.INSTANCE.insert(comment);
    }
    /**
     * Delete a comment object.
     * @param id Id of comment
     */
    public void delete(long id) {
        CommentDAO.INSTANCE.delete(id);
    }
}
