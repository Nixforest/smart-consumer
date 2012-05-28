package com.gae.java.smartconsumer.action.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gae.java.smartconsumer.action.DummyCart;
import com.gae.java.smartconsumer.blo.BillBLO;
import com.gae.java.smartconsumer.blo.BillDetailBLO;
import com.gae.java.smartconsumer.blo.DealBLO;
import com.gae.java.smartconsumer.model.Bill;
import com.gae.java.smartconsumer.model.BillDetail;
import com.gae.java.smartconsumer.model.Deal;
import com.gae.java.smartconsumer.util.GeneralUtil;

public class Order extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (request.getParameter("type") != null) {

            String customerName = request.getParameter("customerName");
            String customerEmail = request.getParameter("customerEmail");
            String customerPhone = request.getParameter("customerPhone");
            String customerAddress = request.getParameter("customerAddress");
            String payment = request.getParameter("payment");
            String cardNumber = request.getParameter("cardNumber");
            String holderName = request.getParameter("holderName");
            String expirationTimeString = request.getParameter("dpickExpirationTime");
            
            Date expirationTime;
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            expirationTime = format.parse(expirationTimeString);
            //System.out.println(payment);
            
            // Insert bill
            Bill bill = new Bill(customerName, customerEmail, customerPhone, customerAddress, Integer.parseInt(payment), cardNumber, holderName, expirationTime);
            BillBLO.INSTANCE.insert(bill);
            Long billId = (long) 0;
            for (Bill innerBill : BillBLO.INSTANCE.getAllBills()) {
                if (innerBill.getCustomerName().equals(customerName)
                        && innerBill.getCustomerEmail().equals(customerEmail)
                        && innerBill.getCardNumber().equals(cardNumber)) {
                    billId = innerBill.getId();
                    System.out.print(billId);
                    
                }
                HttpSession session = request.getSession();
                DummyCart cart = (DummyCart) session.getAttribute("cart");
                cart.processRequest(request);
                String[] items = cart.getItems();
                Map<String, Integer> processItems = new HashMap<String, Integer>();
                //List<Long> processItems = new ArrayList<Long>(); 
                for (int i = 0; i < items.length; i++) {
                    if (!processItems.containsKey(items[i])) {
                        processItems.put(items[i], 1);
                    } else {
                        Integer item = processItems.get(items[i]);
                        processItems.remove(items[i]);
                        processItems.put(items[i], item + 1);
                    }
                }
                for (String key : processItems.keySet()) {
                    BillDetail billDetail = new BillDetail(billId, Long.parseLong(key), processItems.get(key));
                    BillDetailBLO.INSTANCE.insert(billDetail);
                    Deal deal = DealBLO.INSTANCE.getDealById(Long.parseLong(key));
                    deal.setNumberBuyer(deal.getNumberBuyer() + processItems.get(key));
                    
                    DealBLO.INSTANCE.update(deal);
                }
                GeneralUtil.sendMessage("Nixforest21991920@gmail.com", customerEmail, "[Smart Consumer] Thông tin mua hàng", "Chào bạn, cảm ơn bạn đã mua hàng tại SmartConsumer với mã số hóa đơn là " + billId);
            }
        }
        return mapping.findForward("success");
    }
}
