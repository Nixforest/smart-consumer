/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;

/**
 * @author Nixforest
 *
 */
public class AutoUpdateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AutoUpdateServlet.class.getName());
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        log.info("Start AutoUpdateServlet");
        try {
            // update status of deal
            for (Deal deal : DealBLO.INSTANCE.getListAllDeals()) {
                //System.out.println(deal.getEndTime().before(Calendar.getInstance().getTime()));
                if (deal.getEndTime().before(Calendar.getInstance().getTime())) {
                    if (deal.getStatus() == Status.SELLING.ordinal()) {
                        DealBLO.INSTANCE.changeStatus(deal.getId(), Status.OUTOFTIME.ordinal());
                        log.info("Status of deal (id = " + deal.getId() + ") has updated");
                    }
                }
            }
            throw new Exception("Hello hello");
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            //ex.printStackTrace();
            log.severe(ex.getMessage());
        }
        resp.sendRedirect("/smartconsumer");
    }
}
