/**
 * DealBLO.java
 * 21/6/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.blo;

import java.util.ArrayList;
import java.util.List;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

/**
 * Business logic mobile class for Address object.
 * @version 1.0 21/6/2012
 * @author Khoa
 */
public class AddressMobileBLO {
    /**
     * Get a address by Deal Id.
     * @param id
     * @return JSONObject
     */
    public static List<JSONObject> getAddressByDealId(Long id){
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        JSONObject jsonObject = new JSONObject();
        Deal deal = DealBLO.INSTANCE.getDealById(id);
        try {
            Address address = new Address();
            for(int i=0;i<AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(id).size();i++){
                address = AddressBLO.INSTANCE.getAddressById(AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(id).get(i).getAddressId());
                jsonObject.put("id", deal.getId());
                jsonObject.put("title", deal.getTitle());
                jsonObject.put("link", deal.getLink());
                jsonObject.put("linkImage", deal.getImageLink());
                jsonObject.put("price", deal.getPrice());
                jsonObject.put("basicPrice", deal.getBasicPrice());
                jsonObject.put("remainTime", GeneralUtil.getRemainTime(deal.getEndTime()));
                jsonObject.put("fullAddress", address.getFullAddress());
                jsonObject.put("latitude", address.getLatitude());
                jsonObject.put("longitude", address.getLongitude());
                listJson.add(jsonObject);
            }
        } catch (JSONException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }catch (Exception ex1) {
            // TODO Auto-generated catch block
            ex1.printStackTrace();
        }        
        return listJson;
    }
    /**
     * get list address and deal info
     * @param limit
     * @return List<JSONObject>
     */
    public static List<JSONObject> getListAddressLimit(int limit){
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        listDeal = DealBLO.INSTANCE.getDealsLimit(limit);
        if(!listDeal.isEmpty()){
            try{
                Address address = new Address();
                for(int i=0;i<listDeal.size();i++){
                    for(int j=0;j<AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(listDeal.get(i).getId()).size();j++){
                        address = AddressBLO.INSTANCE.getAddressById(AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(listDeal.get(i).getId()).get(j).getAddressId());
                        if(address != null){
                            JSONObject json = new JSONObject();
                            json.put("id", listDeal.get(i).getId());
                            json.put("title", listDeal.get(i).getTitle());
                            json.put("linkImage", listDeal.get(i).getImageLink());
                            json.put("link", listDeal.get(i).getLink());
                            json.put("price", listDeal.get(i).getPrice());
                            json.put("basicPrice", listDeal.get(i).getBasicPrice());
                            json.put("remainTime", GeneralUtil.getRemainTime(listDeal.get(i).getEndTime()));
                            json.put("fullAddress", address.getFullAddress());
                            json.put("latitude", address.getLatitude());
                            json.put("longitude", address.getLongitude());
                            listJson.add(json);
                        }
                    }
                }
            }catch (JSONException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }catch (Exception ex1) {
                // TODO Auto-generated catch block
                ex1.printStackTrace();
            }
        }
        return listJson;
    }
}
