/**
 * GetDealById.java
 * 21/10/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Get deal by Id action for mobile app.
 * @version 1.0 21/10/2012
 * @author Khoa
 */
public class GetDealById extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //response.setContentType("application/json");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write;
        JSONObject object = new JSONObject();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            object = DealMobileBLO.getDealById(id);
            write = response.getWriter();
            write.println(object);
            write.flush();
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
