package com.gae.java.smartconsumer.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.action.DummyCart;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;

public class ViewDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession se = request.getSession();
        if(request.getParameter("item")!=null){
            return mapping.findForward("success1");
        }else{
            
            String param = request.getParameter("id");
            Deal deal = DealBLO.INSTANCE.getDealByTitle(param);
            //System.out.println(deal.getTitle());
            se.setAttribute("deal", deal);
            se.setAttribute("price", GeneralUtil.convertPriceToText(deal.getPrice()));
            se.setAttribute("remainTime", GeneralUtil.getRemainTime(deal.getEndTime()));
            return mapping.findForward("success");
        }
    }
}
