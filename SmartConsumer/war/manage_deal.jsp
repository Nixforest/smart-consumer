<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
    String url = "";
    String urlLinktext = "";
    String nickName = "";
    
    if (request.getAttribute("url") != null) {
        url = (String)request.getAttribute("url");
    }
    if (request.getAttribute("urlLinktext") != null) {
        urlLinktext = (String)request.getAttribute("urlLinktext");
    }
    if (request.getAttribute("nickName") != null) {
        nickName = (String)request.getAttribute("nickName");
    }
    %>
<div style="width: 100%;">
      <div class="line"></div>
      <div class="topLine">
        <div style="float: left;">
          <img src="images/smartconsumer.png" />
        </div>
        <div style="float: left;" class="headline">Quản lý Deal tự tạo</div>
        <div style="float: right;">
          <a href="<%=url%>"><%=urlLinktext%></a>
          <%=(urlLinktext.equals("Login") ? "" : nickName)%></div>
      </div>
    </div>
    
    <div style="clear: both;"></div>
    <!-- <a href="/insert.app">Tạo mới Deal</a> -->
    <a href="/getdeal.app">Cập nhật Deal</a>
<table>
    <tr>
        <td>STT</td>
        <td>Tiêu đề</td>
        <!-- <td>Địa chỉ</td>-->
        <td>Giá bán</td>
        <td>Giá gốc</td>
        <td>Phương thức</td>
        <td>Trạng thái</td>
        <td>Xóa</td>
        <td>Sửa</td>
    </tr>
    
    <logic:iterate id="element" name="listDeals" indexId="index">
    <tr>
        <td><%=index.intValue() + 1%></td>
        <td><bean:write name="element" property="title" /></td>
        
        <td><bean:write name="element" property="price" /></td>
        <td><bean:write name="element" property="basicPrice" /></td>
        <td><bean:write name="element" property="imageLink" /></td>
        <td><a href="/changestatus.app?id=<bean:write name="element" property="id" />"><bean:write name="element" property="link" /></a></td>
        <td><a href="/editdeal.app?id=<bean:write name="element" property="id" />">Sửa</a></td>
        <td><a href="/deldeal.app?id=<bean:write name="element" property="id" />"  onclick="if(!confirm('Bạn có chắc chắn muốn xóa không ?'))return false;">Xóa</a></td>
    </tr>
    </logic:iterate>
    
</table>