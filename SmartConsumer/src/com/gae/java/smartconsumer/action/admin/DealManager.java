/**
 * DealManagerServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller dealmanager
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class DealManager extends Action {
    /**  . */
    private static final long serialVersionUID = 1L;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //RequestDispatcher view = request.getRequestDispatcher("deal.jsp");
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            if (!user.getNickname().toLowerCase().contains("nixforest21991920")
                    && !user.getNickname().toLowerCase().contains("dkhoa47")) {
                response.sendRedirect("/smartconsumer.app");
                return mapping.findForward("failed");
            }  
            request.setAttribute("urlLinktext", "Logout");
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
                          
        } else {
            request.setAttribute("url", "/_ah/login_required?url=dealmanager");
            request.setAttribute("urlLinktext", "Login");

            response.sendRedirect("/smartconsumer.app");
            return mapping.findForward("failed");
        }
        try {
            request.setAttribute("listDeals", DealBLO.INSTANCE.getListAllDeals());
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
}