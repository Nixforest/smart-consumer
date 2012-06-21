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

import com.gae.java.smartconsumer.mobile.blo.AddressMobileBLO;
import com.google.appengine.repackaged.org.json.JSONObject;

public class GetListAddress extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        PrintWriter write;
        List<JSONObject> object = new ArrayList<JSONObject>();
        try{
            int limit = Integer.parseInt(request.getParameter("limit"));
            object = AddressMobileBLO.getListAddressLimit(limit);
            write = response.getWriter();
            write.println(object);
            write.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
