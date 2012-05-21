package com.gae.java.smartconsumer.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.dao.CustomerDAO;
import com.gae.java.smartconsumer.model.BillDetail;

public class ManageBill extends Action{
	
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        List<BillDetail> cus = CustomerDAO.INSTANCE.listCustomers();       
        request.setAttribute("listCustomer", cus);
        return mapping.findForward("success");
    }

}
