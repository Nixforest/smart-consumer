package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;

public class ViewDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession se = request.getSession();
        String param = request.getParameter("id");
        Deal deal = DealBLO.INSTANCE.getDealByTitle(param);
        String price = GeneralUtil.convertPriceToText(deal.getPrice());
        System.out.println("sdfsd");
        //System.out.println(deal.getTitle());
        se.setAttribute("deal", deal);
        se.setAttribute("price1", price);
        return mapping.findForward("success");
    }
}