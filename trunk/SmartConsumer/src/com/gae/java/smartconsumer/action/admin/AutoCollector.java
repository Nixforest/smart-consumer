/**
 * AutoCollector.java
 * 18 Nov 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.gae.java.smartconsumer.util.GetDealFunction;

/**
 * Controller auto collect.
 * @version 1.0 18/11/2012
 * @author NguyenPT
 */
public class AutoCollector extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            // For u-smarconsumer host
            //GetDealFunction.getFromHotDealVn("http://www.hotdeal.vn/ho-chi-minh/");
        } catch (Exception ex) {
            // No statement
            System.out.print(ex.getLocalizedMessage());
        }
        return null;
    }
}
