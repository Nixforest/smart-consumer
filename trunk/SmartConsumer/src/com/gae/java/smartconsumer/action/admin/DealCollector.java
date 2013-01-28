/**
 * DealCollector.java
 * 14/09/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.gae.java.smartconsumer.util.GetDealFunction;
import com.gae.java.smartconsumer.util.GlobalVariable.LIST_LINKS_ID;
import com.gae.java.smartconsumer.util.UtilHtmlToXML;

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
            if (request.getParameter(LIST_LINKS_ID.HOTDEAL.toString()) == "") {
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
                /*GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-3/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/page-4/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-2/");
                GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ha-noi/page-3/");*/
            }
            if (request.getParameter(LIST_LINKS_ID.MUACHUNG.toString()) == "") {
                GetDealFunction.getFromMuaChungVnNew();
            }
            if (request.getParameter(LIST_LINKS_ID.NHOMMUA.toString()) == "") {
                System.out.println(GetDealFunction.getFromNhomMuaCom());
            }
            if (request.getParameter(LIST_LINKS_ID.DEALVIP.toString()).equals("")) {
                System.out.println(
                        GetDealFunction.getFromDealVip(
                                "http://www.dealvip.vn/tp-ho-chi-minh/cung-mua-chung-nhom-mua-hot-deal-khuyen-mai/"));
            }
            if (request.getParameter("url") == "") {
                System.out.println(new UtilHtmlToXML().readHtmlToBuffer(request.getParameter("link")).toString());
                //System.out.println(GetDealFunction.getAddressFromHotDealVn(request.getParameter("link")).toString());
            }
        } catch (Exception ex) {
            String error = ex.getMessage();
            request.setAttribute("error", error);
        }
        return mapping.findForward("success");
    }
}
























