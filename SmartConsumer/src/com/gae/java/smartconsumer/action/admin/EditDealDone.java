package com.gae.java.smartconsumer.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.form.DealForm;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;

public class EditDealDone extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession se = request.getSession();
        DynaValidatorForm dealForm = (DynaValidatorForm)form;
        Long id = (Long)dealForm.get("id");

        Deal deal = DealBLO.INSTANCE.getDealById(id);
        String title = dealForm.getString("title");
        String description = dealForm.getString("description");
        String address = dealForm.getString("address");
        String imageLink = dealForm.getString("imageLink");
        Float price = (Float)dealForm.get("price");
        Float basicPrice = (Float)dealForm.get("basicPrice");
        String unitPrice = dealForm.getString("unitPrice");
        Boolean isVoucher = (Boolean)dealForm.get("isVoucher");
        Date endTime = GeneralUtil.getEndTime(dealForm.getString("endTime"));
        
        deal.setTitle(title);
        deal.setDescription(description);
        //deal.setAddress(address);
        deal.setImageLink(imageLink);
        deal.setPrice(price);
        deal.setBasicPrice(basicPrice);
        deal.setUnitPrice(unitPrice);
        deal.setEndTime(endTime);
        
        DealBLO.INSTANCE.update(deal);
        return mapping.findForward("success");
    }
}
