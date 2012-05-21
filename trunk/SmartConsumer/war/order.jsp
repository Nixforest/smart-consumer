<%@page import="com.gae.java.smartconsumer.util.Payment"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil"%>
<%@page import="com.gae.java.smartconsumer.blo.DealBLO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Đặt hàng</title>
</head>
<body>
<div class="main">
    <h2>Các món hàng đặt mua:</h2>
    <jsp:useBean id="cart" scope="session" class="com.gae.java.smartconsumer.action.DummyCart" />
    <jsp:setProperty name="cart" property="*" />
    <%
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
    <form>
      <table>
          <tr>
              <th>STT</th>
              <th>Mã sản phẩm</th>
              <th>Tên sản phẩm</th>
              <th>Số lượng</th>
              <th colspan="2">Tổng</th>
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
                <td><%=DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getTitle() %></td>
                <td><%=processItems.get(key) %></td>
                <td align="right"><%=processItems.get(key) %> x <%=DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getPrice() %> =</td>
                <td align="right">
                      <%=processItems.get(key)*DealBLO.INSTANCE.getDealById(Long.parseLong(key)).getPrice() %>
                </td>
              </tr>
              <%
          }
          %>
          <tr>
              <td colspan="4">Tổng giá tiền</td>
              <td align="right"><%=total %> =</td>
              <td><%=GeneralUtil.convertPriceToText(total) %></td>
          </tr>
      </table>
      <h2>Thông tin khách hàng:</h2>
      <table>
        <tr>
            <td>Họ tên:</td>
            <td>
                <input type="text" id="customerName" name="customerName"/>
            </td>
            <td>Email:</td>
            <td>
                <input type="text" id="customerEmail" name="customerEmail"/>
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
                <select name="payment">
                    <%
                    for (int j = 0; j < Payment.values().length; j++) {
                        %>
                        <option value="<%=j %>"><%=Payment.values()[j] %></option>
                        <%
                    }
                    %>
                </select>
            </td>
            <td>Mã số thẻ:</td>
            <td>
                <input type="text" id="cardNumber" name="cardNumber"/>
            </td>
        </tr>
        <tr>
            <td>Tên chủ thẻ:</td>
            <td>
                <input type="text" id="holderName" name="holderName"/>
            </td>
            <td>Hạn sử dụng:</td>
            <td>
                <input type="text" id="expirationDateMonth" name="expirationDateMonth"/>:
                <input type="text" id="expirationDateYear" name="expirationDateYear"/>
            </td>
        </tr>
      </table>
      <input type="submit" id="submit" value="Mua"/>
    </form>
</div>
</body>
</html>
