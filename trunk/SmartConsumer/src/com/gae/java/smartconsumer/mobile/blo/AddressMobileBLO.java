/**
 * AddressMobileBLO.java
 * 21/10/2012
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Business logic mobile class for Address object.
 * @version 1.0 21/10/2012
 * @author Khoa
 */
public class AddressMobileBLO {
    /**
     * Constructor.
     */
    protected AddressMobileBLO() {
        // Do nothing
    }
    /**
     * Get a address by Deal Id.
     * @param id Id
     * @return JSONObject
     */
    public static List<JSONObject> getAddressByDealId(Long id) {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        JSONObject jsonObject = new JSONObject();
        Deal deal = DealBLO.INSTANCE.getDealById(id);
        try {
            Address address = new Address();
            for (int i = 0; i < AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(id).size(); i++) {
                address = AddressBLO.INSTANCE.getAddressById(
                        AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(id).get(i).getAddressId());
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
            ex.printStackTrace();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        return listJson;
    }
    /**
     * Get list address and deal info.
     * @param limit Limit
     * @return List<JSONObject>
     */
    public static List<JSONObject> getListAddressLimit(int limit) {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Deal> listDeal = new ArrayList<Deal>();
        //listDeal = DealBLO.INSTANCE.listDealsLimit(limit);
        listDeal = DealBLO.INSTANCE.listDealsSellingSortByUpdateDate();
        if (!listDeal.isEmpty()) {
            try {
                Address address = new Address();
                for (int i = 0; i < listDeal.size(); i++) {
                    for (int j = 0; j < AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(
                            listDeal.get(i).getId()).size(); j++) {
                        address = AddressBLO.INSTANCE.getAddressById(
                                AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(
                                        listDeal.get(i).getId()).get(j).getAddressId());
                        if (address != null) {
                            JSONObject json = new JSONObject();
                            Deal deal = listDeal.get(i);
                            json.put("id", deal.getId());
                            json.put("title", deal.getTitle());
                            json.put("linkImage", deal.getImageLink());
                            json.put("link", deal.getLink());
                            json.put("price", deal.getPrice());
                            json.put("basicPrice", deal.getBasicPrice());
                            json.put("remainTime", GeneralUtil.getRemainTime(deal.getEndTime()));
                            json.put("fullAddress", address.getFullAddress());
                            json.put("latitude", address.getLatitude());
                            json.put("longitude", address.getLongitude());
                            listJson.add(json);
                        }
                    }
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        return listJson;
    }
}
