package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.dao.CustomerDAO;

public class DeleteBill extends Action {

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
                 
        CustomerDAO.INSTANCE.delete(Long.valueOf(request.getParameter("customerid")));
        return mapping.findForward("success");
    }
}
