/**
 * DealManagerServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class DealManagerServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("deal.jsp");
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            if (!user.getNickname().toLowerCase().contains("nixforest21991920")
                    && !user.getNickname().toLowerCase().contains("dkhoa47")) {
                resp.sendRedirect("/smartconsumer.app");
                return;
            }  
            req.setAttribute("urlLinktext", "Logout");
            req.setAttribute("url", userService.createLogoutURL(req.getRequestURI()));
            req.setAttribute("nickName", user.getNickname());
                          
        } else {
            req.setAttribute("url", "/_ah/login_required?url=dealmanager");
            req.setAttribute("urlLinktext", "Login");

            resp.sendRedirect("/smartconsumer.app");
            return;
        }
        try {
            req.setAttribute("listDeals", DealBLO.INSTANCE.getListAllDeals());
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
        }        
        view.forward(req, resp);
    }
}
