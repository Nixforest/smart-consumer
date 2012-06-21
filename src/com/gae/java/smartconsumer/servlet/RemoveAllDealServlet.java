/**
 * RemoveAllDealServlet.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.dao.DealDAO;

/**
 * Controller removealldeal.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class RemoveAllDealServlet extends HttpServlet {
    /** . */
    private static final long serialVersionUID = 1L;
    /**
     * deGet method.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DealDAO.INSTANCE.removeAll();
        resp.sendRedirect("/dealmanager");
    }
}
