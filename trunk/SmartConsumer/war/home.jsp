<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@  page import="com.gae.java.smartconsumer.dao.DealDAO" %>
<%@  page import="com.gae.java.smartconsumer.model.Deal" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Trang chủ</title>
<link rel="stylesheet"
        type="text/css"
        href="css/index.css"/>
</head>
<body class="bg-alt">
    <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String url = "";
    String urlLinktext = "";
    String nickName = "";
    String dealmanager = "";
    
    if (request.getAttribute("url") != null) {
        url = (String)request.getAttribute("url");
    }
    if (request.getAttribute("urlLinktext") != null) {
        urlLinktext = (String)request.getAttribute("urlLinktext");
    }
    if (request.getAttribute("nickName") != null) {
        nickName = (String)request.getAttribute("nickName");
    }
    if (request.getAttribute("dealmanager") != null) {
        dealmanager = (String)request.getAttribute("dealmanager");
    }
            List<Deal> deals = new ArrayList<Deal>();
            deals = (List<Deal>) request.getAttribute("listDeals");
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
          <%=(urlLinktext.equals("Login") ? "" : nickName)%></div>
      </div>
    </div>
    <br/>
    <a href="<%=dealmanager %>">Quản lý Deal</a>
    <a href="/map">Xem bản đồ</a>
    <div style="visibility: hidden; height: 1px; width: 1px; position: absolute; z-index: 100000; " id="_atssh"><iframe id="_atssh280" title="AddThis utility frame" style="height: 1px; width: 1px; position: absolute; z-index: 100000; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-style: initial; border-color: initial; border-image: initial; left: 0px; top: 0px; " src="//s7.addthis.com/static/r07/sh74.html#iit=1333140061219&amp;cb=0&amp;kw=hotdeal%2Chotdeals%2Chot%20deal%2Cmua%20h%C3%A0ng%20theo%20nh%C3%B3m%2Cmua%20chung%2Cnh%C3%B3m%20mua%2Cc%C3%B9ng%20mua%2Cdeal%2Cdeals%2Cgi%C3%A1%20t%E1%BB%91t%2Cgi%C3%A1%20r%E1%BA%BB%2Cgi%E1%BA%A3m%20gi%C3%A1%2Ckhuy%E1%BA%BFn%20m%E1%BA%A1i%2C%C6%B0u%20%C4%91%C3%A3i%2CGroup%20Buy%2Cdaily%20deals&amp;ab=-&amp;dh=www.hotdeal.vn&amp;dr=&amp;du=http%3A%2F%2Fwww.hotdeal.vn%2Fho-chi-minh%2F&amp;dt=HotDeal.vn%3A%20C%C3%B9ng%20mua%20chung%2C%20mua%20theo%20nh%C3%B3m%2C%20mua%20deal%20gi%C3%A1%20r%E1%BA%BB&amp;md=0&amp;inst=1&amp;jsl=33&amp;lng=vi&amp;ogt=&amp;pc=men&amp;pub=hotdeal&amp;ssl=0&amp;sid=4f761a5c66c0b809&amp;srd=1&amp;srf=0.02&amp;srp=0.2&amp;srl=1&amp;srx=1&amp;ver=250&amp;xck=0&amp;xtr=0&amp;og=&amp;rev=109333&amp;ct=1&amp;xld=1&amp;xd=1"></iframe></div>
    <div id="doc">
      <div id="bdw" class="bdw">
        <div id="bd" class="cf">
          <div id="listdeal" style="margin-top:-35px;">
              <%
                  String itemclass = "";
                  for (int i = 0; i < deals.size(); i++) {
                      Deal deal = deals.get(i);
                      //deal = GeneralUtil.decodeDeal(deals.get(i));
                      if (((i + 1) % 3) == 0) {
                          itemclass = "deal_list deal_list_end";
                      } else {
                          itemclass = "deal_list";
                      }
                      %>
                      <div class="<%=itemclass %>">
                          <div class="deal_list_title">
                              <a title="<%=deal.getDescription() %>"
                                  href="<%=deal.getLink() %>">
                                  <%=GeneralUtil.getSubString(deal.getTitle(), 25) %>    
                              </a>
                              <div style="font-size:11px; font-style:italic;">
                                  <%
                                      if (deal.isVoucher()) {
                                          %>
                                          <img align="absmiddle" src="images/voucher-icon.png">
                                          (Giao Voucher)
                                          <%
                                      } else {
                                          %>
                                          <img align="absmiddle" src="images/xe-icon.png">
                                          (Giao Sản phẩm)
                                          <%
                                      }
                                  %>
                              </div>
                          </div>
                          <div class="deal_list_img">
                              <a href="<%=deal.getLink() %>">
                                  <img src="<%=deal.getImageLink() %>"
                                      width="267"
                                      height="183">
                              </a>
                          </div>
                          <div class="deal_list_desc">
                              <%=GeneralUtil.getSubString(deal.getDescription(), 90) %>
                          </div>
                          <div style="width:150px; float:left;">
                              <div class="deal_list_view_price">
                                  <div class="number">
                                      <%=deal.getPrice() %>
                                      <span><%=deal.getUnitPrice() %></span>
                                  </div>
                              </div>
                              <div style="margin-top:0px; float:left; font-style:normal">
                                  <span style="font-size:13px;">
                                      Giá gốc:
                                      <em style="text-decoration:line-through;font-size:13px; color:#666; font-style:normal; ">
                                          <%=deal.getBasicPrice() + " " + deal.getUnitPrice() %>
                                      </em> 
                                  </span>
                              </div>
                          </div>
                          <div class="deal_list_view">
                              <div class="view_list_bt">
                                  <input onclick="window.location='<%=deal.getLink() %>'" 
                                      class="view_bt" 
                                      type="button" name="" value="">
                              </div>
                          </div>
                          <div class="list_view_price">
                              <div class="small-box-save">
                                  <span>Tiết kiệm</span>
                                  <span class="number"><%=deal.getSave() + "%" %></span>
                              </div>
                              <div class="small-box-buyer">
                                  <span>Số người đã mua</span>
                                  <span class="number"><%=deal.getNumberBuyer()%></span>
                              </div>
                              <div class="small-box-timer">
                                  <span>Thời gian còn lại</span>
                                  <span class="number"><%=GeneralUtil.getRemainTime(deal.getEndTime()) %></span>
                              </div>
                          </div>
                      </div>
                      <%
                  }
              %>
              <div style="clear:both">
          </div>
        </div>
      </div>
    </div>
    </div>
</body>
</html>
