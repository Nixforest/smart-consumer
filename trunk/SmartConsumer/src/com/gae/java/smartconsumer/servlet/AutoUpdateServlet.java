/**
 * AutoUpdateServlet.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

/**
 * Controller autoupdate.
 * @version 1.0 28/5/2012
 * @author NguyenPT
 */
public class AutoUpdateServlet extends HttpServlet {
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
            // update status of deal
            for (Deal deal : DealBLO.INSTANCE.listDealsSellingSortByEndTime()) {

                if (deal.getEndTime().before(Calendar.getInstance().getTime())) {
                    DealBLO.INSTANCE.changeStatus(deal.getId(), Status.OUTOFTIME.ordinal());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resp.sendRedirect("/smartconsumer");
    }
}
