package com.gae.java.smartconsumer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.dao.DealDAO;

public class RemoveAllDealServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        DealDAO.INSTANCE.removeAll();
        resp.sendRedirect("/Deal.jsp");
    }
}
