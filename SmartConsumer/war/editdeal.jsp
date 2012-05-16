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

<html:form action="/editdealdone.app" method="POST" onsubmit="return validateEditDeal(this);" >
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
              <td><input type="text" name="tbxtitle" value="<bean:write name="deal" property="title"/>" /></td>            
          </tr>
          <tr>
              <td>Mô tả</td>
              <td><textarea name="tbxdescription" rows="5" cols="25"><bean:write name="deal" property="description" /></textarea></td>            
          </tr>
          <tr>
              <td>Địa chỉ</td>
              <td><input type="text" name="tbxaddress" value="<bean:write name="deal" property="address"/>" /></td>            
          </tr>
          <tr>
              <td>Hình ảnh</td>
              <td><input type="text" name="tbxlinkImage" value="<bean:write name="deal" property="imageLink"/>" /></td>            
          </tr>
          <tr>
              <td>Giá bán</td>
              <td><input type="text" name="tbxprice" value="<bean:write name="deal" property="price" />" /></td>            
          </tr>
          <tr>
              <td>Giá gốc</td>
              <td><input type="text" name="tbxbasicprice" value="<bean:write name="deal" property="basicPrice" />" /></td>            
          </tr>
          <tr>
              <td>Đơn vị</td>
              <td><input type="text" name="tbxunitprice" value="<bean:write name="deal" property="unitPrice" />" /></td>            
          </tr>
          <tr>
              <td>Thời gian khuyến mãi</td>
              <td><input type="text" name="tbxtime" value="<bean:write name="time" />"></td>            
          </tr>
          <tr>
              <td>Phương thức</td>
              <td>
                  <bean:define id="checkvoucher" name="deal" property="Voucher" />
                  <input type="radio" name="isVoucher" value="true" checked='<c:if test="%{#checkvoucher=='true'}">checked</c:if>' />Giao Voucher
                  <input type="radio" name="isVoucher" value="false" checked='<c:if test="%{#checkvoucher=='false'}">checked</c:if>' />Giao sản phẩm
              </td>            
          </tr>
          <tr>
            <td colspan="1"><button type="submit" title="Sửa Deal" id="insertbtn" >Sửa Deal</button></td>
          </tr>
      </table>
  </div>
</html:form>