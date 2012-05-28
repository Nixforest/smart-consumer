/**
 * AutoCollectorServlet.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.util.GetDealFunction;

/**
 * Controller autocollector
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class AutoCollectorServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AutoCollectorServlet.class.getName());
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        log.info("Start AutoCollectorServlet");
        try {
            // 123do
            log.info("Start collect from http://123do.vn/");
            GetDealFunction.getFrom123doVn("http://123do.vn/", 0);
            GetDealFunction.getFrom123doVn("http://123do.vn/dealhot.php", 1);
            
            log.info("Start collect from http://muachung.vn/");
            GetDealFunction.getFromMuaChungVn();

            log.info("Start collect from http://www.hotdeal.vn/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=2");
            GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/?page=3");
        } catch (Exception ex) {
            log.severe(ex.getMessage());
        }
        resp.sendRedirect("/smartconsumer");
    }
}
