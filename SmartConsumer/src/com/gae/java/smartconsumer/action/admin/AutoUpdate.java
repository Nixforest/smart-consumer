/**
 * AutoUpdate.java
 * 6 Nov 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;

/**
 * Controller autoupdate.
 * @version 1.0 28/5/2012
 * @author NguyenPT
 */
public class AutoUpdate extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            // update status of deal
            //for (Deal deal : DealBLO.INSTANCE.listDealsSellingSortByEndTime()) {
            for (Deal deal : DealBLO.INSTANCE.listDealsSortByEndTime()) {
                /*if (deal.getEndTime().before(Calendar.getInstance().getTime())) {
                    //DealBLO.INSTANCE.changeStatus(deal, Status.OUTOFTIME.ordinal());
                    DealBLO.INSTANCE.remove(deal.getId());
                }*/
                DealBLO.INSTANCE.remove(deal.getId());
            }
            for (AddressDetail address : AddressDetailBLO.INSTANCE.getAllAddressDetails()) {
                AddressDetailBLO.INSTANCE.delete(address.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
