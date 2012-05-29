package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.form.StatusForm;

public class ChangeStatusDone extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (request.getParameter("url") == null) {
            StatusForm status = (StatusForm)form;
            //System.out.println(status.getId());
            DealBLO.INSTANCE.changeStatus(status.getId(), status.getStatus());
        } else {
            String url = request.getParameter("url");
        }

        return mapping.findForward("success");
    }
}
