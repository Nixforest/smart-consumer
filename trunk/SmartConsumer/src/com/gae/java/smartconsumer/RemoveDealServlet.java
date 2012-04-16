package com.gae.java.smartconsumer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.dao.DealDAO;

public class RemoveDealServlet extends HttpServlet {

    /**  . */
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String opt = req.getParameter("opt");
        
        if (opt.equals("Remove")) {
            DealBLO.INSTANCE.delete(id);
        } else {
            DealBLO.INSTANCE.restore(id);
        }
        resp.sendRedirect("/deal");
    }
}
