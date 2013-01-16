/**
 * CategoryBLO.java
 * 24 Dec 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.blo;

import java.util.ArrayList;
import java.util.List;

import com.gae.java.smartconsumer.dao.CategoryDAO;
import com.gae.java.smartconsumer.model.Category;

/**
 * Business logic class for Category object.
 * @version 1.0 24/12/2012
 * @author NguyenPT
 */
public enum CategoryBLO {
    /** Instance of class. */
    INSTANCE;
    /**
     * List all categories in data store.
     */
    private List<Category> listCategories = new ArrayList<Category>();
    /**
     * Get all categories in data store.
     * @return List all categories in data store.
     */
    public List<Category> getListCategories() {
        if (this.listCategories.isEmpty()) {
            for (Category item : CategoryDAO.INSTANCE.getListCategories()) {
                this.listCategories.add(item);
            }
        }
        return this.listCategories;
    }
    /**
     * Get category object by id.
     * @param id Id of category
     * @return Category has Id match with parameter.
     */
    public Category getCategoryById(Long id) {
        if (this.isCategoryIdExist(id)) {
            return CategoryDAO.INSTANCE.getCategoryById(id);
        } else {
            return null;
        }
    }
    /**
     * Get a category by Name string.
     * @param name Name of category
     * @return Category has Name match with parameter.
     */
    public Category getCategoryByName(String name) {
        if (this.isCategoryNameExist(name)) {
            for (Category item : this.getListCategories()) {
                if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                    return item;
                }
            }
        }
        return null;
    }
    /**
     * Get list of category has the same parent Id.
     * @param parentId Parent Id of category
     * @return List of category has the same parent Id.
     */
    public List<Category> getListChildrenCategory(Long parentId) {
        if (this.isCategoryIdExist(parentId)) {
            return CategoryDAO.INSTANCE.getListChildrenCategory(parentId);
        } else {
            return null;
        }
    }
    /**
     * Get max Id in data store.
     * @return Max Id
     */
    public Long getMaxId() {
        return CategoryDAO.INSTANCE.getMaxId();
    }
    /**
     * Insert.
     * @param category Category to insert
     * @return Category's Id
     */
    public Long insert(Category category) {
        category.setId(getMaxId() + 1);
        CategoryDAO.INSTANCE.insert(category);
        this.listCategories.add(category);
        return category.getId();
    }
    /**
     * Update an entity.
     * @param category Category to update
     */
    public void update(Category category) {
        if (this.isCategoryIdExist(category.getId())) {
            CategoryDAO.INSTANCE.update(category);
        }
    }
    /**
     * Remove a record by Id.
     * @param id id of record
     */
    public void delete(Long id) {
        if (this.isCategoryIdExist(id)) {
            CategoryDAO.INSTANCE.delete(id);
        }
    }
    /**
     * Check if an id has existed in datastore.
     * @param id Category Id
     * @return True if id has existed, false otherwise.
     */
    public boolean isCategoryIdExist(Long id) {
        for (Category item : this.getListCategories()) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check if an name string has exist in data store.
     * @param name Name string
     * @return True if name has existed, false otherwise.
     */
    public boolean isCategoryNameExist(String name) {
        for (Category item : this.getListCategories()) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
