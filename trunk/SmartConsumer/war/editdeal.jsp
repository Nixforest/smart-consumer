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
$(function() {
    $("#endTime").datetimepicker();
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
function checkAddress() {
    var name = document.getElementById("address");
    if (name.value == "") {
        document.getElementById("errorMes2").removeAttribute("style");
        name.setAttribute("class", "errorInput");
    } else {
        document.getElementById("errorMes2").setAttribute("style",
                "display:none");
        name.removeAttribute("class");
    }

}
function validateField() {
    var docs = document.getElementById("imgLoad");
    var link = document.getElementById("imageLink").value;
    docs.setAttribute("src", link);
    
}
function FormatNumber(str) {

    var strTemp = GetNumber(str);
    if (strTemp.length <= 3)
        return strTemp;
    strResult = "";
    for (var i = 0; i < strTemp.length; i++)
        strTemp = strTemp.replace(",", "");
    var m = strTemp.lastIndexOf(".");
    if (m == -1) {
        for (var i = strTemp.length; i >= 0; i--) {
            if (strResult.length > 0 && (strTemp.length - i - 1) % 3 == 0)
                strResult = "," + strResult;
            strResult = strTemp.substring(i, i + 1) + strResult;
        }
    }
    else {
        //phần nguyên
        var strphannguyen = strTemp.substring(0, strTemp.lastIndexOf("."));
        var strphanthapphan = strTemp.substring(strTemp.lastIndexOf("."), strTemp.length);
        //phần thập phân
        var tam = 0;
        for (var i = strphannguyen.length; i >= 0; i--) {

            if (strResult.length > 0 && tam == 4) {
                strResult = "," + strResult;
                tam = 1;
            }


            strResult = strphannguyen.substring(i, i + 1) + strResult;
            tam = tam + 1;
        }
        strResult = strResult + strphanthapphan;
    }

    return strResult;
}
function GetNumber(str) {
    var count = 0;
    for (var i = 0; i < str.length; i++) {
        var temp = str.substring(i, i + 1);
        if (!(temp == "," || temp == "." || (temp >= 0 && temp <= 9))) {
            alert("Vui lòng nhập số (0-9)!");
            return str.substring(0, i);
        }
        if (temp == " ")
            return str.substring(0, i);
        if (temp == ".") {
            if (count > 0)
                return str.substring(0, i);
            count++;
        }
    }
    return str;
}

function IsNumberInt(str) {
    for (var i = 0; i < str.length; i++) {
        var temp = str.substring(i, i + 1);
        if (!(temp == "." || (temp >= 0 && temp <= 9))) {
            alert("Vui lòng nhập số (0-9)!");
            return str.substring(0, i);
        }
        if (temp == ",") {
            alert("Bạn sử dụng dấu . nếu muốn nhập số lẻ!");
            return str.substring(0, i);
        }
        //                  if(temp == " " || temp == ",")
        //                      return str.substring(0, i);
    }
    return str;
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
              <td>
                <input type="text" size="65" id="address" name="address" value="<bean:write name="deal" property="address"/>"  onblur="checkAddress()"/>
                <span class="errorMes" id="errorMes2" style="display:none">Vui lòng nhập Địa chỉ</span>
              </td>
          </tr>
          <tr>
              <td>Hình ảnh</td>
              <td>
                <input type="url" size="65" id="imageLink" name="imageLink" value="<bean:write name="deal" property="imageLink"/>" />
                <input type="button" name="btnUpload" id="btnUpload" value="Upload" onclick="validateField()" />
                <img id="imgLoad" src='<bean:write name="deal" property="imageLink"/>'>
              </td>
          </tr>
          <tr>
              <td>Giá bán</td>
              <td>
                <input type="text" size="65" id="price" name="price" value="<bean:write name="deal" property="price" />"
                        autocomplete="off"
                        onkeyup="this.value=FormatNumber(this.value);" />
              </td>            
          </tr>
          <tr>
              <td>Giá gốc</td>
              <td>
                <input type="text" size="65" name="basicPrice" value="<bean:write name="deal" property="basicPrice" />"
                        autocomplete="off"
                        onkeyup="this.value=FormatNumber(this.value);" />
              </td>            
          </tr>
          <tr>
              <td>Đơn vị</td>
              <td><input type="text" size="65" name="unitPrice" value="<bean:write name="deal" property="unitPrice" />" /></td>            
          </tr>
          <tr>
              <td>Thời gian kết thúc</td>
              <td>
                <input type="text" size="65" id="endTime" name="endTime" value="<bean:write name="time" />"
                    onblur="checkEndTime()"/>
              </td>            
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