package com.gae.java.smartconsumer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.GetDealFunction;

public class GetDealServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        String link = req.getParameter("link");
        
        if (req.getParameter("123do") == "") {
            GetDealFunction.getFrom123doVn("http://123do.vn/");
        }
        if (req.getParameter("hotdeal") == "") {
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=3");            
        }
        resp.sendRedirect("/Deal.jsp");
    }
}
