<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script type="text/javascript" language="javascript">
$(document).ready(function(){
    //submit
    $("#insertbtn").click(function(){
        var flag = validateInsertDeal();
        if(!flag){
            return false;
        }
    });
});
function checkTitle() {
    var name = document.getElementById("title");
    if (name.value == "") {
        document.getElementById("errorMes1").removeAttribute("style");
        name.setAttribute("class", "errorInput");
    } else {
        document.getElementById("errorMes1").setAttribute("style",
                "display:none");
        name.removeAttribute("class");
    }

}
</script>

<html:form action="/editdealdone.app" method="POST">
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
                    <input type="text" id="title" name="title" size="65" value="<bean:write name="deal" property="title"/>"  onblur="checkTitle()"/>
                    <span class="errorMes" id="errorMes1" style="display:none">Vui lòng nhập Tiêu đề</span>
                    <input type="hidden" name="id" value="<bean:write name="deal" property="id" />"  />
              </td>            
          </tr>
          <tr>
              <td>Mô tả</td>
              <td><textarea name="description" rows="10" cols="50"><bean:write name="deal" property="description" /></textarea></td>            
          </tr>
          <tr>
              <td>Địa chỉ</td>
              <td><input type="text" size="65" name="address" value="<bean:write name="deal" property="address"/>" /></td>            
          </tr>
          <tr>
              <td>Hình ảnh</td>
              <td><input type="text" size="65" name="imageLink" value="<bean:write name="deal" property="imageLink"/>" /></td>            
          </tr>
          <tr>
              <td>Giá bán</td>
              <td><input type="text" size="65" name="price" value="<bean:write name="deal" property="price" />" /></td>            
          </tr>
          <tr>
              <td>Giá gốc</td>
              <td><input type="text" size="65" name="basicPrice" value="<bean:write name="deal" property="basicPrice" />" /></td>            
          </tr>
          <tr>
              <td>Đơn vị</td>
              <td><input type="text" size="65" name="unitPrice" value="<bean:write name="deal" property="unitPrice" />" /></td>            
          </tr>
          <tr>
              <td>Thời gian khuyến mãi</td>
              <td><input type="text" size="65" name="endTime" value="<bean:write name="time" />"></td>            
          </tr>
          <tr>
              <td>Phương thức</td>
              <td>
                  <bean:define id="checkvoucher" name="deal" property="isVoucher" />
                  <input type="radio" name="isVoucher" value="true" <% if(checkvoucher.toString()=="true"){out.print("checked");} %> />Giao Voucher
                  <input type="radio" name="isVoucher" value="false" <% if(checkvoucher.toString()=="false"){out.print("checked");} %> />Giao sản phẩm
              </td>            
          </tr>
          <tr>
            <td colspan="1"><button type="submit" title="Sửa Deal" id="insertbtn" >Sửa Deal</button></td>
          </tr>
      </table>
  </div>
</html:form>