<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script type="text/javascript">
$(document).ready(function(){
	//submit
	$("#insertbtn").click(function(){
		var flag = validateInsertDeal();
		if(!flag){
			return false;
		}
	});
});
</script>
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
        <div style="float: left;" class="headline">Tạo mới Deal</div>
        <div style="float: right;">
          <a href="<%=url%>"><%=urlLinktext%></a>
          <%=(urlLinktext.equals("Login") ? "" : nickName)%></div>
      </div>
    </div>
    <div style="clear: both;"></div>
<html:form action="/insertdeal.app" method="POST" onsubmit="return validateInsertDeal(this);" >
<logic:messagesPresent>
    <ul>
        <font color="red">
            <html:messages id="error">
                <li>${error}</li>
            </html:messages>
        </font>
    </ul>
</logic:messagesPresent>
  <div class="insertdeal">
      <table>
          <tr>
              <td>Tiêu đề</td>
              <td>
                <html:text property="title" size="65" onblur="checkTitle()" />
                <span class="errorMes" id="errorMes1" style="display:none">Vui lòng nhập Tiêu đề</span>
              </td>            
          </tr>
          <tr>
              <td>Mô tả</td>
              <td><html:textarea property="description" cols="50" rows="10"/></td>            
          </tr>
          <tr>
              <td>Địa chỉ</td>
              <td><html:text property="address" size="65" /></td>            
          </tr>
          <tr>
              <td>Hình ảnh</td>
              <td><html:text property="imageLink" size="65" /></td>            
          </tr>
          <tr>
              <td>Giá bán</td>
              <td><html:text property="price" value="0.0" size="65" /></td>            
          </tr>
          <tr>
              <td>Giá gốc</td>
              <td><html:text property="basicPrice" value="0.0" size="65" /></td>            
          </tr>
          <tr>
              <td>Đơn vị</td>
              <td><html:text property="unitPrice" size="65" /></td>            
          </tr>
          <tr>
              <td>Thời gian khuyến mãi</td>
              <td>
                <input type="text" size="65" id="endTime" name="endTime" value="<bean:write name="time" />"
                    onblur="checkEndTime()"/>
              </td>            
          </tr>
          <tr>
              <td>Phương thức</td>
              <td>
                  <input type="radio" name="isVoucher" value="true" checked="checked" />Giao Voucher
                  <input type="radio" name="isVoucher" value="false" />Giao sản phẩm
              </td>            
          </tr>
          <tr>
            <td colspan="1"><button type="submit" title="Thêm Deal" id="insertbtn" >Thêm Deal</button></td>
          </tr>
      </table>
  </div>
</html:form>