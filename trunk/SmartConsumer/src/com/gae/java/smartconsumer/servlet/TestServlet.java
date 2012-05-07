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
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.dao.DealDAO;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;


/**
 * @author Nixforest
 *
 */
public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("testpage.jsp");
        //String error = "Không lỗi!";
        
        long startTime = System.currentTimeMillis();
        URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();
        fetcher.fetchAsync(makeGuestbookPostRequest("Async", "At" + startTime));
        long totalProcessingTime = System.currentTimeMillis() - startTime;
        resp.setContentType("text/html");
        String error = "<h1>Asynchronous fetch demo</h1><br/>" + "<p>Total processing time: " + totalProcessingTime + "ms</p>";
        //resp.getWriter().println("<h1>Asynchronous fetch demo</h1>");
        //resp.getWriter().println("<p>Total processing time: " + totalProcessingTime + "ms</p>");
        req.setAttribute("error", error);
        view.forward(req, resp);
    }
    public static HTTPRequest makeGuestbookPostRequest(String name, String content) {
        HTTPRequest request = null;
        URL url;
        try {
            url = new URL("http://123do.vn/");
            request = new HTTPRequest(url, HTTPMethod.POST);
            String body = "name=" + name + "&amp;content=" + content;
            request.setPayload(body.getBytes());
        } catch (MalformedURLException e) {
        }
        return request;

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("testpage.jsp");
            
        try {
            throw new Exception("Lỗi rồi!");            
        } catch (Exception ex) {
            String error = ex.getMessage();
            req.setAttribute("error", error);
        }
        view.forward(req, resp);
    }
}
