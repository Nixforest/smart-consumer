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
        //System.out.println(GetDealFunction.getFrom123doVn(link));
        //System.out.println(req.getParameter("123do"));
        //System.out.println(req.getParameter("hotdeal"));
        if (req.getParameter("123do") == "") {
            GetDealFunction.getFrom123doVn("http://123do.vn/");
        }
        if (req.getParameter("hotdeal") == "") {
            System.out.println(GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn"));
        }
        resp.sendRedirect("/Deal.jsp");
    }
}
