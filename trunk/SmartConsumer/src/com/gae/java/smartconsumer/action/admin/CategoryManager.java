/**
 * CategoryManager.java
 * 25 Dec 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.CategoryBLO;
import com.gae.java.smartconsumer.model.Category;
import com.gae.java.smartconsumer.util.GlobalVariable;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Controller categorymanager.
 * @version 1.0 24/12/2021
 * @author NguyenPT
 */
public class CategoryManager extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            if (!user.getNickname().toLowerCase().contains(GlobalVariable.NIXFOREST)
                    && !user.getNickname().toLowerCase().contains(GlobalVariable.DUYKHOA)) {
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
        // --- Add new category --- Start
        String name = "";
        String description = "";
        Long parentId = (long) 0;
        String link = "";
        if (request.getParameter("name") != null) {
            name = request.getParameter("name").toString();
        }
        if (request.getParameter("description") != null) {
            description = request.getParameter("description").toString();
        }
        if (request.getParameter("parentId") != null) {
            parentId = Long.parseLong(request.getParameter("parentId").toString());
        }
        if (request.getParameter("link") != null) {
            link = request.getParameter("link").toString();
        }
        if (!name.isEmpty()) {
            Category category = new Category(name, description, parentId, link);
            CategoryBLO.INSTANCE.insert(category);
        }
        // --- Add new category --- End

        try {
            request.setAttribute("listCategories", CategoryBLO.INSTANCE.getListCategories());
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        return mapping.findForward("success");
    }
}
