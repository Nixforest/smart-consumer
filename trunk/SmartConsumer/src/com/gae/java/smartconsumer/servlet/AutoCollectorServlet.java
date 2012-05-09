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

/**
 * @author Nixforest
 *
 */
public class AutoCollectorServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            // 123do
            GetDealFunction.getFrom123doVn("http://123do.vn/", 0);
            GetDealFunction.getFrom123doVn("http://123do.vn/dealhot.php", 1);
            
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=3");
            GetDealFunction.getFromMuaChungVn();
        } catch (Exception ex) {
            //ex.printStackTrace();
            String error = "<code>" + ex.getMessage() + "</code>";
            req.setAttribute("error", error);
        }
        resp.sendRedirect("/smartconsumer");
    }
}
