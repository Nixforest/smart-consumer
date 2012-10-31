/**
 * EditDeal.java
 * 28/05/2012
 * Smart Consumer project
 */
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
/**
 * EditDeal action.
 * @version 1.0 03/06/2012 - Create - NguyenPT
 * @author NguyenPT
 */
public class EditDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession se = request.getSession();
        Long id = Long.valueOf(request.getParameter("id"));
        Deal deal = DealBLO.INSTANCE.getDealById(id);
        se.setAttribute("time", "time");
        String time = GeneralUtil.subDateTime(deal.getEndTime(), deal.getUpdateDate());
        se.setAttribute("deal", deal);
        se.setAttribute("time", time);
        return mapping.findForward("success");
    }
}
