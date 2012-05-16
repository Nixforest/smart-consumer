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
              <td><html:text property="title" /></td>            
          </tr>
          <tr>
              <td>Mô tả</td>
              <td><html:text property="description" /></td>            
          </tr>
          <tr>
              <td>Địa chỉ</td>
              <td><html:textarea property="address" /></td>            
          </tr>
          <tr>
              <td>Hình ảnh</td>
              <td><html:text property="imageLink" /></td>            
          </tr>
          <tr>
              <td>Giá bán</td>
              <td><html:text property="price" value="0.0" /></td>            
          </tr>
          <tr>
              <td>Giá gốc</td>
              <td><html:text property="basicPrice" value="0.0" /></td>            
          </tr>
          <tr>
              <td>Đơn vị</td>
              <td><html:text property="unitPrice" /></td>            
          </tr>
          <tr>
              <td>Thời gian khuyến mãi</td>
              <td><html:text property="endTime" value="00:00:00" /></td>            
          </tr>
          <tr>
              <td>Phương thức</td>
              <td>
                  <input type="radio" name="isVoucher" value="true" checked="checked" />Giao Voucher
                  <input type="radio" name="isVoucher" value="false" />Giao sản phẩm
              </td>            
          </tr>
          <tr>
            <td colspan="1"><button type="submit" title="Sửa Deal" id="insertbtn" >Sửa Deal</button></td>
          </tr>
      </table>
  </div>
</html:form>