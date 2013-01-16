/**
 * CommentDAO.java
 * 8 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Comment;

/**
 * Data access class for Comment object.
 * @version v1.0 08/01/2013
 * @author NguyenPT
 */
public enum CommentDAO {
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
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Comment comment = em.find(Comment.class, id);
            return comment;
        } finally {
            em.close();
        }
    }
    /**
     * Get list comments object by dealID.
     * @param dealId Id of deal
     * @return List comments
     */
    @SuppressWarnings("unchecked")
    public List<Comment> getCommentsByDealId(long dealId) {
        EntityManager em = EMFService.get().createEntityManager();
        String queryString = "select from " + Comment.class.getName()
                + " where dealId=" + dealId;
        Query q = em.createQuery(queryString);
        List<Comment> comments = q.getResultList();
        return comments;
    }
    /**
     * Insert a comment object.
     * @param comment Comment object
     */
    public void insert(Comment comment) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                em.persist(comment);
            } finally {
                em.close();
            }
        }
    }
    /**
     * Delete a comment object.
     * @param id Id of comment
     */
    public void delete(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Comment comment = em.find(Comment.class, id);
            em.remove(comment);
        } finally {
            em.close();
        }
    }
    /**
     * Get max id.
     * @return Max id
     */
    @SuppressWarnings("unchecked")
    public Long getMaxId() {
        EntityManager em = EMFService.get().createEntityManager();
        String queryString = "select from " + Comment.class.getName()
                + " order by id desc";
        try {
            List<Comment> comments = (List<Comment>) em.createQuery(queryString).getResultList();
            if (comments.isEmpty()) {
                return (long) 0;
            } else {
                return comments.get(0).getId();
            }
        } catch (NoResultException ex) {
            return (long) 0;
        }
    }
}
