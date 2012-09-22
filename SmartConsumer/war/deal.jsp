<%@page import="com.gae.java.smartconsumer.blo.AddressDetailBLO"%>
<%@page import="com.gae.java.smartconsumer.model.AddressDetail"%>
<%@page import="com.gae.java.smartconsumer.blo.AddressBLO"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.gae.java.smartconsumer.util.Status"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="java.net.URLDecoder"%>

<%@page import="com.gae.java.smartconsumer.dao.DealDAO" %>
<%@page import="com.gae.java.smartconsumer.blo.DealBLO" %>
<%@page import="com.gae.java.smartconsumer.model.Deal" %>
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
<script type="text/javascript" language="javascript">
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

function setLinkUpdate(id) {
	var name = document.getElementById("updateLink" + id);
    document.getElementById("updateLink" + id).setAttribute("style",
                "display:");
    alert($("#status"+id).val());
    document.getElementById("updateLink" + id).setAttribute("href",
    "/changestatusdone.app?id=" + id + "&status=");
}
</script>
</head>
<body>
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
        List<Deal> deals = (List<Deal>) (request.getAttribute("listDeals"));
        
        
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
    <div style="clear: both;"></div>
    Number of Deals: <%=deals.size() %>
    [<a href="/getdeal">Cập nhật</a>]
    [<a href="/manage.app">Quản lý Deal tự tạo</a>] [<%=DealBLO.INSTANCE.getMaxId() %>]
    <table>
        <tr>
            <th>Id</th>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Địa chỉ</th>
            <th>Link</th>
            <th>Giá</th>
            <th>Giá gốc</th>
            <th>Tiết kiệm</th>
            <th>Người mua</th>
            <th>Thời gian</th>
            <th>Cập nhật</th>
            <th>Status</th>
            <th>Remove</th>
        </tr>
        <%
            for (Deal deal : deals) {
                //deal = GeneralUtil.decodeDeal(deal);
        %>
        <tr>
            <td><%=deal.getId() %></td>
            <td><%=deal.getTitle() %></td>
            <td><%=deal.getDescription() %></td>
            <td>
                <%
                for (AddressDetail item : AddressDetailBLO.INSTANCE.getAddressDetailsByDealId(deal.getId())) {
                    out.print(AddressBLO.INSTANCE.getAddressById(item.getAddressId()).getFullAddress());
                }
                %>
            </td>
            <td><%=deal.getLink() %></td>
            <td><%=deal.getPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getBasicPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getSave() + "%" %></td>
            <td><%=deal.getNumberBuyer() %></td>
            <td><%=GeneralUtil.getRemainTime(deal.getEndTime()) %></td>
            <%-- <td><%=deal.isVoucher() %></td> --%>
            <td><%=deal.getUpdateDate()%></td>
            <td>
                <select id="status<%=deal.getId() %>" name="status<%=deal.getId() %>" onchange="setLinkUpdate(<%=deal.getId() %>)" >
                    <%
                    for (int i = 0; i < Status.values().length; i ++) {
                        %>
                        <option value="<%=i %>" <%if (deal.getStatus() == i) out.print("selected='seleted'");%>><%=Status.values()[i] %></option> 
                        <%
                    }
                    %>
                </select>
                <a class="done" id="updateLink<%=deal.getId() %>" name="updateLink<%=deal.getId() %>" href="" style="display: none;">Update</a>
            </td>
            <td><%=Status.values()[deal.getStatus()] %></td>
            <%
            String statusRemove = "";
            statusRemove = (deal.getStatus() != Status.DELETED.ordinal())?("Remove"):("Restore");
            %>
            <td>
              <a class="done" href="/remove?id=<%=deal.getId() %>&opt=<%=statusRemove %>"><%=statusRemove %></a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
  
    <hr/>
    
    <div class="main" style="display: ;">
        <div class="headline">New deal</div>
        <form action="/dealmanager" method="post" accept-charset="utf-8">
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
                    <td>
                        <input type="url" name="imageLink" id="imageLink" size="65"/>
                        <input type="button" name="btnUpload" id="btnUpload" value="Upload" onclick="validateField()" />
                        <img id="imgLoad" src=''>
                    </td>
                </tr>
                <tr>
                    <td><label for="price">Giá</label></td>
                    <td>
                        <input name="price" id="price" type="text" autocomplete="off"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="basicPrice">Giá gốc</label></td>
                    <td>
                        <input name="basicPrice" id="basicPrice" type="text" autocomplete="off"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="unitPrice">Đơn vị</label></td>
                    <td><input type="text" name="unitPrice" id="unitPrice" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="save">Tiết kiệm</label></td>
                    <td>
                        <input name="save" id="save" type="text" autocomplete="off"
                                onfocusout="ConvertPriceText(this.value)" 
                                onblur="ConvertPriceText(this.value)"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="numberBuyer">Người mua</label></td>
                    <td><input type="number" name="numberBuyer" id="numberBuyer" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="remainTime">Thời gian khuyến mãi</label></td>
                    <td><input type="text" name="remainTime" id="remainTime" size="65" value="00:00:00"/></td>
                </tr>
                <tr>
                    <td><label for="isVoucher">Phương thức</label></td>
                    <td><input type="checkbox" name="isVoucher" id="isVoucher" size="65"/>Giao voucher</td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
