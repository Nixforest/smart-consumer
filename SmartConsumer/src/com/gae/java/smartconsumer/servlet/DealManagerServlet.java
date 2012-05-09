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
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;
import com.google.appengine.api.datastore.Text;

/**
 * @author Nixforest
 *
 */
public class DealManagerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("deal.jsp");
        try {
            req.setAttribute("listDeals", DealBLO.INSTANCE.getListAllDeals());
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
        }        
        view.forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            String title = checkNull(req.getParameter("title"));
            String description = checkNull(req.getParameter("description"));
            String address = checkNull(req.getParameter("address"));
            String link = checkNull(req.getParameter("link"));
            String imageLink = checkNull(req.getParameter("imageLink"));
            double price = Double.parseDouble(GeneralUtil.checkNumber(req.getParameter("price")));
            double basicPrice = Double.parseDouble(GeneralUtil.checkNumber(req.getParameter("basicPrice")));
            String unitPrice = checkNull(req.getParameter("unitPrice"));
            float save = Float.parseFloat(GeneralUtil.checkNumber(req.getParameter("save")));
            int numberBuyer = Integer.parseInt(req.getParameter("numberBuyer"));
            Date endTime = GeneralUtil.getEndTime(req.getParameter("remainTime"));
            //Date remainTime = Calendar.getInstance().
            boolean isVoucher = Boolean.parseBoolean(req.getParameter("isVoucher"));
            Deal deal = new Deal(title, description, address,
                    link, imageLink, price, basicPrice, 
                    unitPrice, save, numberBuyer, endTime,
                    isVoucher);

            //deal = GeneralUtil.encodeDeal(deal);
            DealBLO.INSTANCE.insert(deal);
        } catch (Exception ex) {
            RequestDispatcher view = req.getRequestDispatcher("deal.jsp");
            String error = ex.getMessage();
            req.setAttribute("error", error);
            view.forward(req, resp);
        }
        resp.sendRedirect("/dealmanager");
    }

    private String checkNull(String s) {
        // TODO Auto-generated method stub
        if (s == null) {
            return "";
        }
        return s;
    }
}
