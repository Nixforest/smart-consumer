/**
 * DealManager.java
 * 28/05/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.action.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GlobalVariable;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller dealmanager.
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class DealManager extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            if (!user.getNickname().toLowerCase().contains("nixforest21991920")
                    && !user.getNickname().toLowerCase().contains("dkhoa47")) {
                response.sendRedirect("/smartconsumer.app");
                return mapping.findForward("failed");
            }
            request.setAttribute("urlLinktext", GlobalVariable.LOGOUT);
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
        } else {
            request.setAttribute("url", "/_ah/login_required?url=dealmanager");
            request.setAttribute("urlLinktext", GlobalVariable.LOGIN);

            response.sendRedirect("/smartconsumer.app");
            return mapping.findForward("failed");
        }
        try {
            // Get List Deal sort by Update Day
            List<Deal> listDeal = new ArrayList<Deal>();
            for (Deal item : DealBLO.INSTANCE.listDealsSortByUpdateDate()) {
                listDeal.add(item);
            }
            // Reverse this list
            Collections.reverse(listDeal);
            request.setAttribute("listDeals", listDeal);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
}
