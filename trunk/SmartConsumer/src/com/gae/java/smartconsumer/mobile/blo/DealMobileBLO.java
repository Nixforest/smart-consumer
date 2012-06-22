/**
 * DealBLO.java
 * 2/6/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.blo;

import java.util.ArrayList;
import java.util.List;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

/**
 * Business logic mobile class for Deal object.
 * @version 1.0 2/6/2012
 * @author Khoa
 */
public class DealMobileBLO {
    /**
     * Get a deal by Id.
     * @param id id of Deal
     * @return JSONObject
     */
    public static JSONObject getDealById(Long id) throws JSONException {
        Deal deal = DealBLO.INSTANCE.getDealById(id);
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
        return jsonObject;
    }
    
    /**
     * 
     * get list all selling deal
     * @return List<JSONObject>
     * @throws JSONException
     */
    public static List<JSONObject> getDealListAll() throws JSONException{
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.getListAllDealsSelling();
        if(!listDeal.isEmpty()){
            for(int i=0;i<listDeal.size();i++){
                JSONObject json = new JSONObject();
                json.put("id", listDeal.get(i).getId());
                json.put("title", listDeal.get(i).getTitle());
                json.put("description", listDeal.get(i).getDescription());
                json.put("link", listDeal.get(i).getLink());
                json.put("imageLink", listDeal.get(i).getImageLink());
                json.put("price", listDeal.get(i).getPrice());
                json.put("basicPrice", listDeal.get(i).getBasicPrice());
                json.put("unitPrice", listDeal.get(i).getUnitPrice());
                json.put("save", listDeal.get(i).getSave());
                json.put("numberBuyer", listDeal.get(i).getNumberBuyer());
                json.put("endTime", listDeal.get(i).getEndTime());
                json.put("isVoucher", listDeal.get(i).getisVoucher());
                listJson.add(json);
            }
        }        
        return listJson;
    }
    /**
     * limit get deal
     * @param limit
     * @return List<JSONObject>
     * @throws JSONException
     */
    public static List<JSONObject> getDealLimit(int limit) throws JSONException{
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.getDealsLimit(limit);
        if(!listDeal.isEmpty()){
            for(int i=0;i<listDeal.size();i++){
                JSONObject json = new JSONObject();
                json.put("id", listDeal.get(i).getId());
                json.put("title", listDeal.get(i).getTitle());
                json.put("description", listDeal.get(i).getDescription());
                json.put("link", listDeal.get(i).getLink());
                json.put("imageLink", listDeal.get(i).getImageLink());
                json.put("price", listDeal.get(i).getPrice());
                json.put("basicPrice", listDeal.get(i).getBasicPrice());
                json.put("unitPrice", listDeal.get(i).getUnitPrice());
                json.put("save", listDeal.get(i).getSave());
                json.put("numberBuyer", listDeal.get(i).getNumberBuyer());
                json.put("endTime", listDeal.get(i).getEndTime());
                json.put("isVoucher", listDeal.get(i).getisVoucher());
                listJson.add(json);
            }
        }
        return listJson;
    }
}
