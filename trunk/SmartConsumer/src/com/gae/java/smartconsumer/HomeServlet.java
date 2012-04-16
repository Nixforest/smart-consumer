package com.gae.java.smartconsumer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;

public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("home.jsp");
        req.setAttribute("listDeals", DealBLO.INSTANCE.listDealsSellingSortByUpdateDate());
        view.forward(req, resp);
    }
}
