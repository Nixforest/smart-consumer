package com.gae.java.smartconsumer.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorForm;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.form.DealForm;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.gae.java.smartconsumer.util.Status;

public class InsertDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /*DealForm dealForm = (DealForm)form;
        System.out.println(dealForm.getTitle());
        System.out.println(dealForm.getDescription());
        System.out.println(dealForm.getAddress());
        System.out.println(dealForm.getPrice());
        System.out.println(dealForm.getBasicPrice());
        System.out.println(dealForm.getUnitPrice());
        System.out.println(dealForm.getVoucher());link image link save endtime number buy update date status*/
        try{
            DynaValidatorForm dealForm = (DynaValidatorForm)form;
            String title = dealForm.getString("title");
            String description = dealForm.getString("description");
            String address = dealForm.getString("address");
            String imageLink = dealForm.getString("imageLink");
            Float price = (Float)dealForm.get("price");
            Float basicPrice = (Float)dealForm.get("basicPrice");
            String unitPrice = dealForm.getString("unitPrice");
            Boolean isVoucher = (Boolean)dealForm.get("isVoucher");
            int numberBuyer = 0;
            float save = basicPrice - price;
            String link = "/viewdeal.app?id="+ GeneralUtil.ReplaceNotation(GeneralUtil.RemoveSign4VietNameseString(title)," ","-");
            
            //change status ...
            Date endTime = GeneralUtil.getEndTime(dealForm.getString("endTime"));
            int status = Status.WAITTOCHECK.ordinal();     
            Deal deal = new Deal(title, description, address, link, imageLink, price, 
                    basicPrice, unitPrice, save, numberBuyer, endTime, isVoucher, status);
            
            DealBLO.INSTANCE.insert(deal);
            return mapping.findForward("success");
        }catch(Exception e){
            System.out.println(e.getMessage());
            return mapping.findForward("failed");
        }
    }
}
