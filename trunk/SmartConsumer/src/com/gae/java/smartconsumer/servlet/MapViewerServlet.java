/**
 * MapViewerServlet.java
 * 
 * 28/5/2012
 * 
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

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller mapviewer
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class MapViewerServlet extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            request.setAttribute("urlLinktext", "Logout");
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
                          
        } else {
            request.setAttribute("url", "/_ah/login_required?url=map");
            request.setAttribute("urlLinktext", "Login");
        }
        try {
            List<Deal> listDeals = new ArrayList<Deal>();
            for (Deal deal : DealBLO.INSTANCE.getListAllDeals()) {                
                if (!checkIfAddressExist(listDeals, deal.getAddress())) {
                    listDeals.add(deal);
                }
            }
            request.setAttribute("listDeals", listDeals);
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
        for (Deal deal : deals) {
            if (deal.getAddress().contains(address)) {
                return true;
            }
        }
        return false;
    }
}
