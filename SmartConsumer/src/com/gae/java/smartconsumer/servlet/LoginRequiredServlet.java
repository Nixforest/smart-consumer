/**
 * LoginRequiredServlet.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.ProviderOpenID;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller login.
 * @version 1.0 28/5/2012
 * @author NguyenPT
 */
public class LoginRequiredServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 1L;
    /**
     * Map authentication domain.
     */
    private static final Map<ProviderOpenID, String> AUTHDOMAINMAP = new HashMap<ProviderOpenID, String>();
    static {
        AUTHDOMAINMAP.put(ProviderOpenID.GOOGLE, "google.com");
        AUTHDOMAINMAP.put(ProviderOpenID.YAHOO, "yahoo.com");
    }
    /**
     * Map federate identity.
     */
    private static final Map<ProviderOpenID, String> FEDERATEDIDENTITYMAP;
    static {
        FEDERATEDIDENTITYMAP = new HashMap<ProviderOpenID, String>();
        FEDERATEDIDENTITYMAP.put(ProviderOpenID.GOOGLE, "https://www.google.com/accounts/o8/id");
        FEDERATEDIDENTITYMAP.put(ProviderOpenID.YAHOO, "yahoo.com");
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
            for (ProviderOpenID providerName : FEDERATEDIDENTITYMAP.keySet()) {
                String providerUrl = FEDERATEDIDENTITYMAP.get(providerName);
                //String loginUrl = userService.createLoginURL(req.getRequestURI(), null,
                  //          "gmail.com",  new HashSet<String>());
                String loginUrl = userService.createLoginURL(
                        req.getRequestURI(), AUTHDOMAINMAP.get(providerName),
                        providerUrl, new HashSet<String>());
                out.println("[<a href=\"" + loginUrl + "\">" + providerName + "</a>] ");
            }
        }
    }
}
