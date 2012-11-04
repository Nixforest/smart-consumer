/**
 * MapViewerServlet.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Address;
import com.gae.java.smartconsumer.model.AddressDetail;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GlobalVariable;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller mapviewer.
 * @version 1.0 28/05/2012
 * @author NguyenPT
 */
public class MapViewerServlet extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            request.setAttribute("urlLinktext", GlobalVariable.LOGOUT);
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
        } else {
            request.setAttribute("url", "/_ah/login_required?url=map");
            request.setAttribute("urlLinktext", GlobalVariable.LOGIN);
        }
        try {
            List<Address> listAddresses = new ArrayList<Address>();
            for (Deal deal : DealBLO.INSTANCE.getListAllDealsSelling()) {
                List<AddressDetail> listAddressDetails =
                        AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(deal.getId());
                for (AddressDetail addressDetail : listAddressDetails) {
                    listAddresses.add(AddressBLO.INSTANCE.getAddressById(addressDetail.getAddressId()));
                }
            }
            request.setAttribute("listDeals", DealBLO.INSTANCE.getListAllDealsSelling());
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
    /**
     * Check if an address is exist.
     * @param deals List deals
     * @param address address to check
     * @return True if address is exist, false otherwise
     */
    public boolean checkIfAddressExist(List<Deal> deals, String address) {
        return false;
    }
}
