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

import com.gae.java.smartconsumer.mobile.blo.DealMobileBLO;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

public class GetListDeal extends Action {
    /**
     * 
     * [Explain the description for this method here].
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write;
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        try {
            if (request.getParameter("limit")!=null) {
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
        } catch(JSONException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
