/**
 * GetCommentsByDealId.java
 * 9 Jan 2013
 * SmartConsumer.
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

import com.gae.java.smartconsumer.mobile.blo.CommentMobileBLO;

/**
 * Get comments by deal id action for mobile app.
 * @version 1.0 09/01/2013
 * @author NguyenPT
 */
public class GetCommentsByDealId extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //response.setContentType("application/json");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write;
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        try {
            if (request.getParameter("dealId") != null) {
                long dealId = Long.parseLong(request.getParameter("dealId"));
                listJson = CommentMobileBLO.getCommentsByDealId(dealId);
                write = response.getWriter();
                write.println(listJson);
                write.flush();
            }
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
