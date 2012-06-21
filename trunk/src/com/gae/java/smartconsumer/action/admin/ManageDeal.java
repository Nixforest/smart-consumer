package com.gae.java.smartconsumer.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.Status;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ManageDeal extends Action {
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
            request.setAttribute("urlLinktext", "Logout");
            request.setAttribute("url", userService.createLogoutURL(request.getRequestURI()));
            request.setAttribute("nickName", user.getNickname());
                          
        } else {
            request.setAttribute("url", "/_ah/login_required?url=dealmanager");
            request.setAttribute("urlLinktext", "Login");

            response.sendRedirect("/smartconsumer.app");
            return mapping.findForward("failed");
        }
        HttpSession se = request.getSession();
        List<Deal> list = new ArrayList<Deal>();
        List<Deal> listDeal = new ArrayList<Deal>();
        list = DealBLO.INSTANCE.listDealByCreate();
        for(Deal item : list){
            //set id by the way using description
            //item.setDescription(GeneralUtil.ReplaceNotation(GeneralUtil.RemoveSign4VietNameseString(item.getTitle())," ","-"));
            if(item.getisVoucher()==true){
                item.setImageLink("Giao Voucher");
            }else{
                item.setImageLink("Giao sản phẩm");
            }
            if(item.getStatus()==Status.SELLING.ordinal()){
                item.setLink("Đang bán");
            }else if(item.getStatus()==Status.OUTOFTIME.ordinal()){
                item.setLink("Hết hạn");
            }else if(item.getStatus()==Status.DELETED.ordinal()){
                item.setLink("Đã xóa");
            }else if(item.getStatus()==Status.WAITTOCHECK.ordinal()){
                item.setLink("Chưa duyệt");
            }
            listDeal.add(item);
        }
        request.setAttribute("listDeal", listDeal);
        return mapping.findForward("success");
    }
}
