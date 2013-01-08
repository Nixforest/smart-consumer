/**
 * CategoryDAO.java
 * 24 Dec 2012
 * SmartConsumer project.
 */
package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Category;

/**
 * Data access class for Category object.
 * @version 1.0 24/12/2012
 * @author NguyenPT
 */
public enum CategoryDAO {
    /**
     * Intance of class.
     */
    INSTANCE;
    /**
     * Get all categories in data store.
     * @return List all categories in data store.
     */
    @SuppressWarnings("unchecked")
    public List<Category> getListCategories() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Category.class.getName());
        List<Category> categories = q.getResultList();
        return categories;
    }
    /**
     * Get category object by id.
     * @param id Id of category
     * @return Category has Id match with parameter.
     */
    public Category getCategoryById(Long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Category category = em.find(Category.class, id);
            return category;
        } finally {
            em.close();
        }
    }
    /**
     * Get list of category has the same parent Id.
     * @param parentId Parent Id of category
     * @return List of category has the same parent Id.
     */
    @SuppressWarnings("unchecked")
    public List<Category> getListChildrenCategory(Long parentId) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Category.class.getName() + " where parentId=" + parentId);
        List<Category> categories = q.getResultList();
        return categories;
    }
    /**
     * Get max Id in data store.
     * @return Max Id
     */
    @SuppressWarnings("unchecked")
    public Long getMaxId() {
        Long maxId = (long) 0;
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select from " + Category.class.getName()
                + " order by id desc");
        List<Category> listId = q.getResultList();
        if (listId.size() != 0) {
            maxId = listId.get(0).getId();
        }
        return maxId;
    }
    /**
     * Insert a category to data store.
     * @param category Category to insert
     */
    public void insert(Category category) {
        synchronized (this) {
            EntityManager em = EMFService.get().createEntityManager();
            try {
                em.persist(category);
            } finally {
                em.close();
            }
        }
    }
    /**
     * Update an entity.
     * @param category Category to update
     */
    public void update(Category category) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Category innerCategory = em.find(Category.class, category.getId());
            innerCategory.setName(category.getName());
            innerCategory.setDescription(category.getDescription());
            innerCategory.setParentId(category.getParentId());
            innerCategory.setLink(category.getLink());
        } finally {
            em.close();
        }
    }
    /**
     * Remove a record by Id.
     * @param id id of record
     */
    public void delete(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Category category = em.find(Category.class, id);
            em.remove(category);
        } finally {
            em.close();
        }
    }
}
