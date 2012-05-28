<%@page import="com.gae.java.smartconsumer.util.Payment"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.gae.java.smartconsumer.model.Bill" %>
<%@page import="com.gae.java.smartconsumer.model.BillDetail" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();
List<Bill> listBills = (List<Bill>) request.getAttribute("listBills");
List<BillDetail> listBillDetails = (List<BillDetail>) request.getAttribute("listBillDetails");
%>
<%-- <div class="main">
<h2>Thông tin khách hàng</h2>
<table>
    <tr>
      <td>Họ tên:</td>
      <td>
          <%=user.getNickname() %>
      </td>
      <td>Email:</td>
      <td>
          <%=user.getEmail() %>
      </td>
  </tr>
  <tr>
      <td>Số điện thoại:</td>
      <td>
          <%=listBills.get(0).getCustomerPhone() %>
      </td>
      <td>Địa chỉ:</td>
      <td>
          <%=listBills.get(0).getCustomerAddress() %>
      </td>
  </tr>
  <tr>
      <td>Hình thức thanh toán:</td>
      <td>
          <%=Payment.values()[listBills.get(0).getPayment()] %>
      </td>
      <td>Mã số thẻ:</td>
      <td>
          <%=listBills.get(0).getCardNumber() %>
      </td>
  </tr>
  <tr>
      <td>Tên chủ thẻ:</td>
      <td>
          <%=listBills.get(0).getHolderName() %>
      </td>
      <td>Hạn sử dụng:<span class="errorMes" id="errorMes6" style="display:none">(*)</span></td>
      <td>
          <%=listBills.get(0).getExpirationDate() %>
      </td>
  </tr>
</table>
</div> --%>