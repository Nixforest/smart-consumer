/**
 * GetListDeal.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import com.gae.java.smartconsumer.mobile.blo.DealMobileBLO;
/**
 * Get list deals class.
 * @version 1.0 28/05/2012
 * @author KhoaCD
 */
public class GetListDeal extends Action {
   @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write;
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        try {
            if (request.getParameter("limit") != null) {
                int limit = Integer.valueOf(request.getParameter("limit"));
                listJson = DealMobileBLO.getDealLimit(limit);
                write = response.getWriter();
                write.println(listJson);
                write.flush();
            } else {
                listJson = DealMobileBLO.getDealListAll();
                write = response.getWriter();
                write.println(listJson);
                write.flush();
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
