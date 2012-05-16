/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.util.ProviderOpenID;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet control authentication.
 * 
 * @author Nixforest
 */
public class LoginRequiredServlet extends HttpServlet {
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
        Set attributesRequest = new HashSet();
        attributesRequest.add("openid.mode=checkid_immediate");
        attributesRequest.add("openid.ns=http://specs.openid.net/auth/2.0");
        attributesRequest.add("openid.return_to=/smartconsumer");
        
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
                        providerUrl, new HashSet());
                log.info(loginUrl);
                out.println("[<a href=\"" + loginUrl + "\">" + providerName + "</a>] ");
            }
        }
    }
}
