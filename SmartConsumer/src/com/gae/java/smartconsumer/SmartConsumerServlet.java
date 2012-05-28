/**
 * SmartConsumerServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller smartconsumer
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class SmartConsumerServlet extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            request.setAttribute("urlLinktext", "Logout");
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
            if (user.getNickname().toLowerCase().contains("nixforest21991920")
                    || user.getNickname().toLowerCase().contains("dkhoa47")) {
                request.setAttribute("dealmanager", "/dealmanager");
            }                
        } else {
            request.setAttribute("url", "/_ah/login_required?url=smartconsumer");
            request.setAttribute("urlLinktext", "Login");
        }
        try {
            request.setAttribute("listDeals", DealBLO.INSTANCE.listDealsSellingSortByUpdateDate());
            request.setAttribute("listDealsCreated", DealBLO.INSTANCE.listDealSellingByCreate());
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
}