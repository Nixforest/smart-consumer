<%@page import="com.gae.java.smartconsumer.util.Payment"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil"%>
<%@page import="com.gae.java.smartconsumer.blo.DealBLO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Đặt hàng</title>
<link rel="stylesheet" type="text/css" href="js/jquery-ui-1.8.20.custom/css/ui-lightness/jquery-ui-1.8.20.custom.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-ui-1.8.20.custom.min.js"></script>  
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-ui-timepicker-addon.js"></script>
<script>
  $(function() {
      $("#dpickExpirationTime").datepicker();
  });
  
  function checkCustomerName() {   
    var field = document.getElementById("customerName");
    if (field.value == "") {
        document.getElementById("errorMes1").removeAttribute("style");
        field.setAttribute("class", "errorInput");
    } else {
        document.getElementById("errorMes1").setAttribute("style",
                "display:none");
        field.removeAttribute("class");
    }
  }
  function checkCustomerEmail(email) {   
      var field = document.getElementById("customerEmail");
      if (field.value == "") {
          document.getElementById("errorMes2").removeAttribute("style");
          field.setAttribute("class", "errorInput");
      } else {
    	  document.getElementById("errorMes2").setAttribute("style",
          "display:none");
          field.removeAttribute("class");
    	  var emailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
          if (emailReg.test(field.value)) {
        	  document.getElementById("errorMes3").setAttribute("style",
              "display:none");
              field.removeAttribute("class");
          } else {
        	  document.getElementById("errorMes3").removeAttribute("style");
              field.setAttribute("class", "errorInput");
          }
          
      }
  }
  function checkCardNumber() {
      var field = document.getElementById("cardNumber");
      if (field.value == "") {
          document.getElementById("errorMes4").removeAttribute("style");
          field.setAttribute("class", "errorInput");
      } else {
          document.getElementById("errorMes4").setAttribute("style",
                  "display:none");
          field.removeAttribute("class");
      }
  }
  function checkHolderName() {
      var field = document.getElementById("holderName");
      if (field.value == "") {
          document.getElementById("errorMes5").removeAttribute("style");
          field.setAttribute("class", "errorInput");
      } else {
          document.getElementById("errorMes5").setAttribute("style",
                  "display:none");
          field.removeAttribute("class");
      }
  }
  function checkExpirationTime() {
      var field = document.getElementById("dpickExpirationTime");
      if (field.value == "") {
          document.getElementById("errorMes6").removeAttribute("style");
          field.setAttribute("class", "errorInput");
      } else {
          document.getElementById("errorMes6").setAttribute("style",
                  "display:none");
          field.removeAttribute("class");
      }
  }
</script>
</head>
<body>
<div class="main">
    <h2>Các món hàng đặt mua:</h2>
    <jsp:useBean id="cart" scope="session" class="com.gae.java.smartconsumer.action.DummyCart" />
    <jsp:setProperty name="cart" property="*" />
    <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    
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
    %>
    <div class="lorder">
    <form action="order.app?type=submit" method="POST" onsubmit="return validateOrder(this);">
      <table>
          <tr>
              <th>STT</th>
              <th>Mã sản phẩm</th>
              <th class="lorderproduct">Tên sản phẩm</th>
              <th>Số lượng</th>
              <th colspan="2" class="lordersum">Tổng</th>
          </tr>
          <%
          int i = 0;
          double total = 0;
          for (String key : processItems.keySet()) {
              i++;
              total += processItems.get(key)*DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getPrice();
              %>
              <tr>
                <td><%=i %></td>
                <td><%=key %></td>
                <td class="lordertitle"><%=DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getTitle() %></td>
                <td class="lordertitle"><%=processItems.get(key) %></td>
                <td align="right" class="lordertitle"><%=processItems.get(key) %> x <%=DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getPrice() %> =</td>
                <td align="right" class="lordersum">
                      <%=processItems.get(key)*DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getPrice() %>
                </td>
              </tr>
              <%
          }
          %>
          <tr>
              <td colspan="4" class="lordertitle">Tổng giá tiền</td>
              <td align="right" class="lorderall"><%=total %> =</td>
              <td class="lordersum"><%=GeneralUtil.convertPriceToText(total) %></td>
          </tr>
      </table>
      <h2>Thông tin khách hàng:</h2>
      <table>
        <tr>
            <td>Họ tên:<span class="errorMes" id="errorMes1" style="display:none">(*)</span></td>
            <td>
                <input type="text" id="customerName" name="customerName"
                    value="<%=(user != null ? user.getNickname() : "") %>"
                    onblur="checkCustomerName()"/>
                
            </td>
            <td>Email:<span class="errorMes" id="errorMes2" style="display:none">(*)</span></td>
            <td>
                <input type="text" id="customerEmail" name="customerEmail"
                    value="<%=(user != null ? user.getEmail() : "") %>"
                    onblur="checkCustomerEmail()"/>
                    <span class="errorMes" id="errorMes3" style="display:none">Email chưa hợp lệ</span>
            </td>
        </tr>
        <tr>
            <td>Số điện thoại:</td>
            <td>
                <input type="text" id="customerPhone" name="customerPhone"/>
            </td>
            <td>Địa chỉ:</td>
            <td>
                <input type="text" id="customerAddress" name="customerAddress"/>
            </td>
        </tr>
        <tr>
            <td>Hình thức thanh toán:</td>
            <td>
                <select name="payment" id="payment">
                    <%
                    for (int j = 0; j < Payment.values().length; j++) {
                        %>
                        <option value="<%=j %>"><%=Payment.values()[j] %></option>
                        <%
                    }
                    %>
                </select>
            </td>
            <td>Mã số thẻ:<span class="errorMes" id="errorMes4" style="display:none">(*)</span></td>
            <td>
                <input type="text" id="cardNumber" name="cardNumber"
                    onblur="checkCardNumber()"/>
            </td>
        </tr>
        <tr>
            <td>Tên chủ thẻ:<span class="errorMes" id="errorMes5" style="display:none">(*)</span></td>
            <td>
                <input type="text" id="holderName" name="holderName"
                    onblur="checkHolderName()"/>
            </td>
            <td>Hạn sử dụng:<span class="errorMes" id="errorMes6" style="display:none">(*)</span></td>
            <td>
                <input type="text" id="dpickExpirationTime" name="dpickExpirationTime"
                    onblur="checkExpirationTime()"/>
            </td>
        </tr>
      </table>
      <input type="submit" id="submit" value="Mua"/>
    </form>
    </div>
</div>
</body>
</html>
