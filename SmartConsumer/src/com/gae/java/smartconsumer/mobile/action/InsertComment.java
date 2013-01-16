/**
 * InsertComment.java
 * 9 Jan 2013
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
import com.gae.java.smartconsumer.model.Comment;

/**
 * Insert new comment action for mobile app.
 * @version 1.0 09/01/2013
 * @author NguyenPT
 */
public class InsertComment extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Long dealId = (long) 0;
        String username = "";
        String content = "";
        if (request.getParameter("dealId") != null) {
            dealId = Long.parseLong(request.getParameter("dealId"));
        }
        if (request.getParameter("username") != null) {
            username = request.getParameter("username").toString();
        }
        if (request.getParameter("content") != null) {
            content = request.getParameter("content").toString();
        }
        Comment comment = new Comment(dealId, username, content);
        CommentBLO.INSTANCE.insert(comment);
        return null;
    }
}
