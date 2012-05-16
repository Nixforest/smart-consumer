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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
 * @author Nixforest
 *
 */
/*public class MapViewerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("map.jsp");
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
            req.setAttribute("urlLinktext", "Logout");
            req.setAttribute("url", userService.createLogoutURL(req.getRequestURI()));
            req.setAttribute("nickName", user.getNickname());
                          
        } else {
            req.setAttribute("url", "/_ah/login_required?url=map");
            req.setAttribute("urlLinktext", "Login");
        }
        try {
            List<Deal> listDeals = new ArrayList<Deal>();
            for (Deal deal : DealBLO.INSTANCE.listDealsSellingSortByUpdateDate()) {                
                if (!checkIfAddressExist(listDeals, deal.getAddress())) {
                    listDeals.add(deal);
                }
            }
            req.setAttribute("listDeals", listDeals);
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
        }        
        view.forward(req, resp);
    }
    
    public boolean checkIfAddressExist(List<Deal> deals, String address) {
        for (Deal deal : deals) {
            if (deal.getAddress().contains(address)) {
                return true;
            }
        }
        return false;
    }
}*/
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
        //view.forward(req, resp);
        return mapping.findForward("success");
    }
    public boolean checkIfAddressExist(List<Deal> deals, String address) {
        for (Deal deal : deals) {
            if (deal.getAddress().contains(address)) {
                return true;
            }
        }
        return false;
    }
}
