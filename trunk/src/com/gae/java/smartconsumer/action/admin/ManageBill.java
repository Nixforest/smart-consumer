package com.gae.java.smartconsumer.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.BillBLO;
import com.gae.java.smartconsumer.blo.BillDetailBLO;
import com.gae.java.smartconsumer.model.Bill;
import com.gae.java.smartconsumer.model.BillDetail;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ManageBill extends Action{
	
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    List<Bill> listBills = new ArrayList<Bill>();
        for (Bill bill : BillBLO.INSTANCE.getAllBills()) {
            if (bill.getCustomerName().equals(user.getNickname())) {
                listBills.add(bill);
            }
        }
        request.setAttribute("listBills", listBills);
        request.setAttribute("listBillDetails", BillDetailBLO.INSTANCE.getAllBillDetails());
        return mapping.findForward("success");
    }

}
