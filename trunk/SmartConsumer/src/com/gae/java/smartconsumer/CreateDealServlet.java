package com.gae.java.smartconsumer;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.dao.DealDAO;

@SuppressWarnings("serial")
public class CreateDealServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        String title = checkNull(req.getParameter("title"));
        String description = checkNull(req.getParameter("description"));
        String address = checkNull(req.getParameter("address"));
        String link = checkNull(req.getParameter("link"));
        String imageLink = checkNull(req.getParameter("imageLink"));
        double price = Double.parseDouble(req.getParameter("price"));
        double basicPrice = Double.parseDouble(req.getParameter("basicPrice"));
        String unitPrice = checkNull(req.getParameter("unitPrice"));
        float save = Float.parseFloat(req.getParameter("save"));
        int numberBuyer = Integer.parseInt(req.getParameter("numberBuyer"));
        String remainTime = String.valueOf(req.getParameter("remainTime"));
        boolean isVoucher = Boolean.parseBoolean(req.getParameter("isVoucher"));
        
        try {
            DealDAO.INSTANCE.insert(title, description, address, link,
                    imageLink, price, basicPrice, unitPrice, save,
                    numberBuyer,
                    remainTime, 
                    true);
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
        resp.sendRedirect("/Deal.jsp");
    }

    private String checkNull(String s) {
        // TODO Auto-generated method stub
        if (s == null) {
            return "";
        }
        return s;
    }
}
