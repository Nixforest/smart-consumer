<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.gae.java.smartconsumer.dao.Dao" %>
<%@  page import="com.gae.java.smartconsumer.model.Deal" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Get Deal from websites</title>
<link rel="stylesheet"
        type="text/css"
        href="css/main.css"/>
</head>
<body>
    <%
        Dao dao = Dao.INSTANCE;
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
    
        String url = userService.createLoginURL(request.getRequestURI());
        String urlLinktext = "Login";
        List<Deal> deals = new ArrayList<Deal>();
        
        if (user != null) {
            url = userService.createLogoutURL(request.getRequestURI());
            urlLinktext = "Logout";
        }
        deals = dao.listDeals();
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
    <div class="linkchoose">
        <form action="/getdeal" method="get" accept-charset="utf-8">
          <table>
              <tr>
                  <td><input type="radio" id="123do" name="123do" value="http://123do.vn/"/>http://123do.vn/</td>
              </tr>
              <tr>
                  <td><input type="radio" id="hotdeal" name="hotdeal" value="http://www.hotdeal.vn"/>http://www.hotdeal.vn</td>
              </tr>
            <tr>
              <td align="right"><input type="submit" value="Lọc thông tin" /></td>
            </tr>
          </table>
        </form>
    </div>
</body>
</html>
