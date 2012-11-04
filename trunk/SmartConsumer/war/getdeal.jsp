<!-- Get deal from hotdeal/muachung... -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.gae.java.smartconsumer.dao.DealDAO" %>
<%@page import="com.gae.java.smartconsumer.model.Deal" %>
<%@page import="com.gae.java.smartconsumer.util.GlobalVariable" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title><%=GlobalVariable.DEAL_COLLECTOR %></title>
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
            String urlLinktext = GlobalVariable.LOGIN;
            List<Deal> deals = new ArrayList<Deal>();
            
            if (user != null) {
              url = userService.createLogoutURL(request.getRequestURI());
              urlLinktext = GlobalVariable.LOGOUT;
            }
            deals = dao.getListAllDeals();
            String error = (String)request.getAttribute("error");
    %>
    <div class="errorview">
        <%
        if (error != null) {
            %>
            <label><%=error %></label>
            <%
        }
        %>        
    </div>
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
        <form action="/getdeal.app" method="post" accept-charset="utf-8">
          <table>
              <tr>
                  <td><input type="checkbox" id="123do" name="123do" value="" disabled="disabled"/><%=GlobalVariable.LINK_123DO %></td>
              </tr>
              <tr>
                  <td><input type="checkbox" id="hotdeal" name="hotdeal" value=""/><%=GlobalVariable.LINK_HOTDEAL %></td>
              </tr>
              <tr>
                  <td><input type="checkbox" id="muachung" name="muachung" value=""/><%=GlobalVariable.LINK_MUACHUNG %></td>
              </tr>
              <tr>
                  <td><input type="checkbox" id="nhommua" name="nhommua" value="" disabled="disabled"/><%=GlobalVariable.LINK_NHOMMUA %></td>
              </tr>
              <tr>
                  <td><input type="checkbox" id="cungmua" name="cungmua" value="" disabled="disabled"/><%=GlobalVariable.LINK_CUNGMUA %></td>
              </tr>
              <tr>
                  <td>
                    <input type="checkbox" id="url" name="url" value=""/>
                    <label for="link">URL: </label><input type="text" id="link" name="link" />
                  </td>
              </tr>
              <tr>
                <td align="right"><input type="submit" value="Lọc thông tin" /></td>
              </tr>
          </table>
        </form>
    </div>
</body>
</html>
