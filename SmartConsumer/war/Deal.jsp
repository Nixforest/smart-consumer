<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@  page import="com.gae.java.smartconsumer.dao.DealDAO" %>
<%@  page import="com.gae.java.smartconsumer.model.Deal" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Deal Information</title>
<link rel="stylesheet"
        type="text/css"
        href="css/main.css"/>
</head>
<body>
    <%
        DealDAO dao = DealDAO.INSTANCE;
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
    
        String url = userService.createLoginURL(request.getRequestURI());
        String urlLinktext = "Login";
        List<Deal> deals = new ArrayList<Deal>();
        
        if (user != null) {
          url = userService.createLogoutURL(request.getRequestURI());
          urlLinktext = "Logout";
        }
        deals = dao.listDealsSortByUpdateDate();
    %>
    <div style="width: 100%;">
      <div class="line"></div>
      <div class="topLine">
        <div style="float: left;">
          <img src="images/smartconsumer.png" />
        </div>
        <div style="float: left;" class="headline">Deals Information</div>
        <div style="float: right;">
          <a href="<%=url%>"><%=urlLinktext%></a>
          <%=(user == null ? "" : user.getNickname())%></div>
      </div>
    </div>
    <div style="clear: both;"></div>
    Number of Deals: <%=deals.size() %>
    [<a href="/GetDeal.jsp">Cập nhật</a>]
    [<a href="/removeall">Xóa tất cả</a>]
    <table>
        <tr>
            <th>Id</th>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Địa chỉ</th>
            <th>Link</th>
            <th>Ảnh</th>
            <th>Giá</th>
            <th>Giá gốc</th>
            <th>Tiết kiệm</th>
            <th>Người mua</th>
            <th>Thời gian</th>
            <!-- <th>IsVoucher</th> -->
            <th>Cập nhật</th>
            <th>Remove</th>
        </tr>
        <%
            for (Deal deal : deals) {
        %>
        <tr>
            <td><%=deal.getId() %></td>
            <td><%=deal.getTitle() %></td>
            <td><%=deal.getDescription() %></td>
            <td><%=deal.getAddress() %></td>
            <td><%=deal.getLink() %></td>
            <td><%=deal.getImageLink() %></td>
            <td><%=deal.getPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getBasicPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getSave() + "%" %></td>
            <td><%=deal.getNumberBuyer() %></td>
            <td><%=GeneralUtil.getRemainTime(deal.getEndTime()) %></td>
            <%-- <td><%=deal.isVoucher() %></td> --%>
            <td width="30px"><%=deal.getUpdateDate()%></td>
      <td><a class="done" href="/remove?id=<%=deal.getId() %>">Remove</a></td>
        </tr>
        <%
            }
        %>
    </table>
  
    <hr/>
    
    <div class="main">
        <div class="headline">New deal</div>
        <form action="/new" method="post" accept-charset="utf-8">
            <table>
                <tr>
                    <td><label for="title">Tiêu đề</label></td>
                    <td><input type="text" name="title" id="title" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="description">Mô tả</label></td>
                    <td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
                </tr>
                <tr>
                    <td><label for="address">Địa chỉ</label></td>
                    <td><input type="text" name="address" id="address" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="link">URL</label></td>
                    <td><input type="url" name="link" id="link" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="imageLink">Ảnh</label></td>
                    <td><input type="url" name="imageLink" id="imageLink" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="price">Giá</label></td>
                    <td><input type="number" name="price" id="price" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="basicPrice">Giá gốc</label></td>
                    <td><input type="number" name="basicPrice" id="basicPrice" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="unitPrice">Đơn vị</label></td>
                    <td><input type="text" name="unitPrice" id="unitPrice" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="save">Tiết kiệm</label></td>
                    <td><input type="number" name="save" id="save" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="numberBuyer">Người mua</label></td>
                    <td><input type="number" name="numberBuyer" id="numberBuyer" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="remainTime">Thời gian còn lại</label></td>
                    <td><input type="text" name="remainTime" id="remainTime" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="isVoucher">Phương thức</label></td>
                    <td><input type="checkbox" name="isVoucher" id="isVoucher" size="65"/></td>
                </tr>
                <tr>
                  <td colspan="2" align="right"><input type="submit" value="Tạo mới" /></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
