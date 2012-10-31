/**
 * DeleteDeal.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
/**
 * DeleteDeal action.
 * @version 1.0 03/06/2012 - Create - NguyenPT
 * @author NguyenPT
 */
public class DeleteDeal extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        DealBLO.INSTANCE.delete(id);
        return mapping.findForward("success");
    }
}
