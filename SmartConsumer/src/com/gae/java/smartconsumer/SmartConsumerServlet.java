package com.gae.java.smartconsumer;
import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.util.ProviderOpenID;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class SmartConsumerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
            RequestDispatcher view = req.getRequestDispatcher("home.jsp");
            
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            
            if (user != null) {
                req.setAttribute("urlLinktext", "Logout");
                req.setAttribute("url", userService.createLogoutURL(req.getRequestURI()));
                req.setAttribute("nickName", user.getNickname());
                if (user.getNickname().equals("Nixforest21991920")
                        || user.getNickname().equals("dkhoa47")) {
                    req.setAttribute("dealmanager", "/dealmanager");
                }                
            } else {
                req.setAttribute("url", "/_ah/login_required?url=smartconsumer");
                req.setAttribute("urlLinktext", "Login");
            }
            try {
                req.setAttribute("listDeals", DealBLO.INSTANCE.listDealsSellingSortByUpdateDate());
            } catch (Exception ex) {
                req.setAttribute("error", ex.getMessage());
            }            
            view.forward(req, resp);
        }
}
