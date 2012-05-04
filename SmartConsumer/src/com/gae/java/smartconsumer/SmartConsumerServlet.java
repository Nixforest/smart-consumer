package com.gae.java.smartconsumer;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gae.java.smartconsumer.blo.DealBLO;

@SuppressWarnings("serial")
public class SmartConsumerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
            RequestDispatcher view = req.getRequestDispatcher("home.jsp");
            try {
                req.setAttribute("listDeals", DealBLO.INSTANCE.listDealsSellingSortByUpdateDate());
            } catch (Exception ex) {
                req.setAttribute("error", ex.getMessage());
            }            
            view.forward(req, resp);
        }
}
