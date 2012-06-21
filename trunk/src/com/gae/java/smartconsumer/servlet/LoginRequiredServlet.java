/**
 * LoginRequiredServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.ProviderOpenID;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller login
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class LoginRequiredServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LoginRequiredServlet.class.getName());
    private static final Map<ProviderOpenID, String> authDomainMap = new HashMap<ProviderOpenID, String>();
    static {
        authDomainMap.put(ProviderOpenID.GOOGLE, "google.com");
        authDomainMap.put(ProviderOpenID.YAHOO, "yahoo.com");
    }
    private static final Map<ProviderOpenID, String> federatedIdentityMap;
    static {
        federatedIdentityMap = new HashMap<ProviderOpenID, String>();
        federatedIdentityMap.put(ProviderOpenID.GOOGLE, "https://www.google.com/accounts/o8/id");
        federatedIdentityMap.put(ProviderOpenID.YAHOO, "yahoo.com");
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        if (user != null) {
            out.println("Hello <i>" + user.getNickname() + "</i>!");
            out.println("[<a href=\""
                    + userService.createLogoutURL(req.getRequestURI())
                    + "\">sign out</a>]");
        } else {
            out.println("Hello world! Sign in at: ");
            for (ProviderOpenID providerName : federatedIdentityMap.keySet()) {
                String providerUrl = federatedIdentityMap.get(providerName);
                log.info(providerUrl);
                //String loginUrl = userService.createLoginURL(req.getRequestURI(), null,
                  //          "gmail.com",  new HashSet<String>());
                String loginUrl = userService.createLoginURL(
                        req.getRequestURI(), authDomainMap.get(providerName),
                        providerUrl, new HashSet<String>());
                log.info(loginUrl);
                out.println("[<a href=\"" + loginUrl + "\">" + providerName + "</a>] ");
            }
        }
    }
}
