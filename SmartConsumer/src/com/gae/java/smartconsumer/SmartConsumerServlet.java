/**
 * SmartConsumerServlet.java
 * 28/5/2012
 * Smart Consumer project
 */
package com.gae.java.smartconsumer;

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
 * Controller smartconsumer.
 * @version 1.0 28/5/2012
 * @author NguyenPT
 */
public class SmartConsumerServlet extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
            request.setAttribute("urlLinktext", GlobalVariable.LOGOUT);
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
            if (user.getNickname().toLowerCase().contains(GlobalVariable.NIXFOREST)
                    || user.getNickname().toLowerCase().contains(GlobalVariable.DUYKHOA)) {
                request.setAttribute("dealmanager", "/dealmanager");
            }
        } else {
            request.setAttribute("url", "/_ah/login_required?url=smartconsumer");
            request.setAttribute("urlLinktext", GlobalVariable.LOGIN);
        }
        try {
            /*
            String strCurrentPage = request.getParameter("currentPage");
            int currentPage = 1;
            int countOfActiveDeal = DealBLO.INSTANCE.getCountOfActiveDeal();
            if (strCurrentPage != null) {
                currentPage = Integer.parseInt(strCurrentPage);
            }*/
            // Get List Deal sort by Update Day
            List<Deal> listDeal = new ArrayList<Deal>();
            for (Deal item : DealBLO.INSTANCE.listDealsSellingSortByUpdateDate()) {
                listDeal.add(item);
            }
            // Reverse this list
            Collections.reverse(listDeal);
            request.setAttribute("listDeals", listDeal);
            //request.setAttribute("countOfActiveDeal", countOfActiveDeal);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
}
