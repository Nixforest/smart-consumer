/**
 * DeleteCommentById.java
 * 10 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.mobile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.gae.java.smartconsumer.blo.CommentBLO;

/**
 * Delete a comment action for mobile app.
 * @version 1.0 10/01/2013
 * @author NguyenPT
 */
public class DeleteCommentById extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Long id = (long) -1;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }
        CommentBLO.INSTANCE.delete(id);
        return null;
    }
}
