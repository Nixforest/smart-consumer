/**
 * AutoCollectorServlet.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.GetDealFunction;

/**
 * Controller autocollector.
 * @version 1.0 28/5/2012
 * @author NguyenPT
 */
public class AutoCollectorServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 1L;
    /**
     * Do Get.
     * @param req Request
     * @param resp Response
     * @throws IOException Exception
     * @throws ServletException ServerletException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            GetDealFunction.getFromMuaChungVnNew();
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        resp.sendRedirect("/smartconsumer");
    }
}
