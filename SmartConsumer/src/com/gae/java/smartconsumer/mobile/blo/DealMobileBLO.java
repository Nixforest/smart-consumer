/**
 * DealMobileBLO.java
 * 21/10/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.blo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.CategoryBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.Category;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Business logic mobile class for Deal object.
 * @version 1.0 21/10/2012
 * @author Khoa
 */
public class DealMobileBLO {
    /**
     * Constructor.
     */
    protected DealMobileBLO() {
        // Do nothing
    }
    /**
     * Get a deal by Id.
     * @param id id of Deal
     * @return JSONObject Object json
     * @throws JSONException Exception
     */
    public static JSONObject getDealById(Long id) throws JSONException {
        Deal deal = DealBLO.INSTANCE.getDealById(id);
        JSONObject jsonObject = new JSONObject();
        if (!deal.equals(null)) {
            jsonObject = convertDealToJSONObject(deal);
        }
        return jsonObject;
    }
    /**
     * Get list all selling deal.
     * @return List<JSONObject>
     * @throws JSONException Exception
     */
    public static List<JSONObject> getDealListAll() throws JSONException {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.getListAllDealsSelling();
        if (!listDeal.isEmpty()) {
            for (int i = 0; i < listDeal.size(); i++) {
                listJson.add(convertDealToJSONObject(listDeal.get(i)));
            }
        }
        return listJson;
    }
    /**
     * Limit get deal.
     * @param limit Limit
     * @return List<JSONObject>
     * @throws JSONException Exception
     */
    public static List<JSONObject> getDealLimit(int limit) throws JSONException {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.listDealsLimit(limit);
        if (!listDeal.isEmpty()) {
            for (int i = 0; i < listDeal.size(); i++) {
                listJson.add(convertDealToJSONObject(listDeal.get(i)));
            }
        }
        return listJson;
    }
    /**
     * Get list deals by category id.
     * @param categoryId Id of category
     * @return List Deals by category id
     * @throws JSONException Exception
     */
    public static List<JSONObject> getDealsByCategoryId(Long categoryId) throws JSONException {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.getDealsByCategoryId(categoryId);
        for (Deal deal : listDeal) {
            listJson.add(convertDealToJSONObject(deal));
        }
        return listJson;
    }
    /**
     * Convert a deal to JSONObject.
     * @param deal Deal object
     * @return JSONObject
     * @throws JSONException Exception
     */
    private static JSONObject convertDealToJSONObject(Deal deal) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", deal.getId());
        jsonObject.put("title", deal.getTitle());
        jsonObject.put("description", deal.getDescription());
        jsonObject.put("link", deal.getLink());
        jsonObject.put("imageLink", deal.getImageLink());
        jsonObject.put("price", deal.getPrice());
        jsonObject.put("basicPrice", deal.getBasicPrice());
        jsonObject.put("unitPrice", deal.getUnitPrice());
        jsonObject.put("save", deal.getSave());
        jsonObject.put("numberBuyer", deal.getNumberBuyer());
        jsonObject.put("endTime", deal.getEndTime());
        jsonObject.put("isVoucher", deal.getisVoucher());
        // NguyenPT - 2012/12/23 - Start
        boolean isExistAddress = false;
        double longitude = 0.0;
        double latitude = 0.0;
        String fullAddress = "";
        if (AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(deal.getId()).size() != 0) {
            isExistAddress = true;
            Address address = AddressBLO.INSTANCE.getAddressById(
                    AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(deal.getId()).get(0).getAddressId());
            longitude = address.getLongitude();
            latitude = address.getLatitude();
            fullAddress = address.getFullAddress();
        }
        jsonObject.put("isExistAddress", isExistAddress);
        jsonObject.put("longitude", longitude);
        jsonObject.put("latitude", latitude);
        jsonObject.put("fullAddress", fullAddress);
        // NguyenPT - 2012/12/23 - End
        jsonObject.put("categoryId", deal.getCategoryId());
        Category category = CategoryBLO.INSTANCE.getCategoryById(deal.getCategoryId());
        if (category != null) {
            jsonObject.put("categoryName", category.getName());
        } else {
            jsonObject.put("categoryName", "");
        }
        return jsonObject;
    }
}
