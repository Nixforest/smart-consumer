package com.gae.java.smartconsumer.action.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;

public class SearchDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        double priceFrom = Double.parseDouble(request.getParameter("searchPriceFrom"));
        double priceTo = Double.parseDouble(request.getParameter("searchPriceTo"));
        List<Deal> lst = DealBLO.INSTANCE.searchByPrice(priceFrom, priceTo);
        session.setAttribute("listDeal", lst);
        return mapping.findForward("success");
    }
}