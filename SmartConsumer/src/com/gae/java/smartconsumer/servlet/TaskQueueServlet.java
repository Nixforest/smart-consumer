/**
 * TaskQueue.java
 * 
 * 28/5/2012
 * 
 * Smart Consumer project
 */
package com.gae.java.smartconsumer.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;


/**
 * Controller taskqueue
 * 
 * @version 1.0 28/5/2012
 * @author Nixforest
 */
public class TaskQueueServlet extends HttpServlet {
    /**  . */
    private static final long serialVersionUID = 7769838809830051533L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        for (int i = 0; i < 100; i++) {
            Queue queue = QueueFactory.getDefaultQueue();
            queue.add(withUrl("/autocollector").method(Method.GET));
        }
    }
}
