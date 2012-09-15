/**
 * DealCollector.java
 * 14 Sep 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.AddressBLO;
import com.gae.java.smartconsumer.blo.AddressDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.util.GetDealFunction;

/**
 * Deal collector.
 * @version 2.0 - 14/09/2012 - NguyenPT
 * @author NguyenPT
 */
public class DealCollector extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            if (request.getParameter("123do") == "") {
                GetDealFunction.getFrom123doVn("http://123do.vn/", 0);
                GetDealFunction.getFrom123doVn("http://123do.vn/dealhot.php", 1);
            }
            if (request.getParameter("hotdeal") == "") {
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-3/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-4/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-3/");
            }
            if (request.getParameter("muachung") == "") {
                GetDealFunction.getFromMuaChungVn();
            }
            DealBLO.INSTANCE.insertIntoDataStore();
            AddressBLO.INSTANCE.insertIntoDataStore();
            AddressDetailBLO.INSTANCE.insertIntoDataStore();
        } catch (Exception ex) {
            String error = ex.getMessage();
            request.setAttribute("error", error);
        }
        return mapping.findForward("success");
    }
}
