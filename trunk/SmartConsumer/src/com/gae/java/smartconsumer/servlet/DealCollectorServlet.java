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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.GetDealFunction;
import com.gae.java.smartconsumer.util.UtilHtmlToXML;

/**
 * @author Nixforest
 *
 */
public class DealCollectorServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("getdeal.jsp");
        
        view.forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("getdeal.jsp");
        try {
            if (req.getParameter("123do") == "") {
                GetDealFunction.getFrom123doVn("http://123do.vn/");
               //throw new Exception(GetDealFunction.getFrom123doVnY("http://123do.vn/"));
            }
            //System.out.println(req.getParameter("hotdeal"));
            if (req.getParameter("hotdeal") == "") {
                System.out.println(GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn"));
                //System.out.println(GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/?page=2"));
                /*GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=2");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=3");*/
            }
            if (req.getParameter("muachung") == "") {
                //throw new Exception(GetDealFunction.getFrom123doVnY(req.getParameter("link")));
                GetDealFunction.getFromMuaChungVn();
            }
            if (req.getParameter("cungmua") == "") {
                System.out.println(GetDealFunction.getFromCungMuaCom());
            }
            if (req.getParameter("test").isEmpty()) {
                //throw new Exception(GetDealFunction.getFrom123doVnX("http://123do.vn/"));
            }
            if (req.getParameter("url") == "") {
                //throw new Exception(req.getParameter("link"));
                //throw new Exception(GetDealFunction.getFrom123doVnY(req.getParameter("link")));
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            String error = "<code>" + ex.getMessage() + "</code>";
            req.setAttribute("error", error);
        }
        view.forward(req, resp);
    }
}
