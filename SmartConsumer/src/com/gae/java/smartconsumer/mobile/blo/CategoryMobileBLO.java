/**
 * CategoryMobileBLO.java
 * 16 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.mobile.blo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.gae.java.smartconsumer.blo.CategoryBLO;
import com.gae.java.smartconsumer.model.Category;

/**
 * Business logic mobile class for Category object.
 * @version 1.0 16/01/2013
 * @author NguyenPT
 */
public class CategoryMobileBLO {
    /**
     * Constructor.
     */
    protected CategoryMobileBLO() {
        // Do nothing
    }
    /**
     * Get category by id.
     * @param id Id of category
     * @return JSONObject
     * @throws JSONException Exception
     */
    public static JSONObject getCategoryById(Long id) throws JSONException {
        Category category = CategoryBLO.INSTANCE.getCategoryById(id);
        if (category.equals(null)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", category.getId());
        jsonObject.put("name", category.getName());
        jsonObject.put("description", category.getDescription());
        jsonObject.put("link", category.getLink());
        jsonObject.put("parentId", category.getParentId());
        return jsonObject;
    }
    /**
     * Get list all categories.
     * @return List all categories
     * @throws JSONException Exception
     */
    public static List<JSONObject> getCategories() throws JSONException {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Category> categories = CategoryBLO.INSTANCE.getListCategories();
        for (Category category : categories) {
            listJson.add(convertCategoryToJSONObject(category));
        }
        return listJson;
    }
    /**
     * Convert a category to JSONObject.
     * @param category Category object
     * @return JSONObject
     * @throws JSONException Exception
     */
    public static JSONObject convertCategoryToJSONObject(Category category) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", category.getId());
        jsonObject.put("name", category.getName());
        jsonObject.put("description", category.getDescription());
        jsonObject.put("link", category.getLink());
        jsonObject.put("parentId", category.getParentId());
        return jsonObject;
    }
}
