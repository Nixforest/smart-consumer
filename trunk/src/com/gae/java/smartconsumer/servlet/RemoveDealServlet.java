/**
 * RemoveDealServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;

/**
 * Controller removedeal
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class RemoveDealServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 5303631542336734271L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {            
            Long id = Long.parseLong(req.getParameter("id"));
            String opt = req.getParameter("opt");
            
            if (opt.equals("Remove")) {
                DealBLO.INSTANCE.delete(id);
            } else {
                DealBLO.INSTANCE.restore(id);
            }

        } catch (Exception ex) {
            // TODO: handle exception
        }
        resp.sendRedirect("/dealmanager");
    }
}
