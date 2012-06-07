/**
 * DealCollectorServlet.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.GetDealFunction;
import com.gae.java.smartconsumer.util.UtilHtmlToXML;

/**
 * Controller dealcollector.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class DealCollectorServlet extends HttpServlet {
    /** . */
    private static final long serialVersionUID = 1L;
    /**
     * doGet method for DealCollectorServlet.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     * @throws ServletException ServletException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("getdeal.jsp");

        view.forward(req, resp);
    }
    /**
     * doPost method for DealCollectorServlet.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     * @throws ServletException ServletException
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("getdeal.jsp");
        try {
            if (req.getParameter("123do") == "") {
                GetDealFunction.getFrom123doVn("http://123do.vn/", 0);
                GetDealFunction.getFrom123doVn("http://123do.vn/dealhot.php", 1);
            }

            if (req.getParameter("hotdeal") == "") {
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-3/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-4/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-3/");
            }
            if (req.getParameter("muachung") == "") {
                System.out.println(GetDealFunction.getFromMuaChungVn());
            }
            if (req.getParameter("nhommua") == "") {
                System.out.println("adfd");
                System.out.println(GetDealFunction.getFromNhomMuaCom());
            }
            if (req.getParameter("url") == "") {
                System.out.println("adfd");
                System.out.println(new UtilHtmlToXML().readHtmlToBuffer(req.getParameter("link")).toString());
            }
        } catch (Exception ex) {
            String error = ex.getMessage();
            req.setAttribute("error", error);
        }
        view.forward(req, resp);
    }
}
