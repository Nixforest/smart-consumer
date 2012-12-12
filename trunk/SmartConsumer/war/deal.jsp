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
<%@page import="com.gae.java.smartconsumer.util.GlobalVariable" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title><%=GlobalVariable.DEAL_INFO %></title>
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
    List<Deal> deals = new ArrayList<Deal>();
    if (request.getAttribute("listDeals") != null) {
        deals = (List<Deal>) request.getAttribute("listDeals");    
    }
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
        <div style="float: left;" class="headline"><%=GlobalVariable.DEAL_INFO %></div>
        <div style="float: right;">
          <a href="<%=url%>"><%=urlLinktext%></a>
          <%=(urlLinktext.equals(GlobalVariable.LOGIN) ? "" : nickName)%></div>
      </div>
    </div>
    <div style="clear: both;"></div>
    <!-- List deal in data store -->
    <%=GlobalVariable.DEAL_NUMBER %>: <%=deals.size() %>
    [<a href="/getdeal.app"><%=GlobalVariable.UPDATE %></a>]
    <form name="frm" action="dealmanager.app" method="post">
            <%
            int currentPage = 1;
            int startRecord = 0;
            if (request.getParameter("currentPage") != null) {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
                if (currentPage == 1) {
                    startRecord = 0;
                } else {
                    startRecord = (currentPage - 1) * GlobalVariable.DEAL_PER_PAGE_DEALMANAGER;
                }
            } else {
                startRecord = 0;
            }
            List<Deal> listSubDeals = new ArrayList<Deal>();
            for (int i = 0; i < deals.size(); i++) {
                if ((i >= startRecord)
                        && (i < startRecord + GlobalVariable.DEAL_PER_PAGE_DEALMANAGER)) {
                    listSubDeals.add(deals.get(i));
                }
            }
            %>
      <table>
          <tr>
              <th><%=GlobalVariable.DEAL_ID %></th>
              <th><%=GlobalVariable.DEAL_TITLE %></th>
              <th><%=GlobalVariable.DEAL_DESCRIPTION %></th>
              <th><%=GlobalVariable.DEAL_ADDRESS %></th>
              <th><%=GlobalVariable.DEAL_LINK %></th>
              <th><%=GlobalVariable.DEAL_PRICE %></th>
              <th><%=GlobalVariable.DEAL_BASIC_PRICE %></th>
              <th><%=GlobalVariable.DEAL_SAVE %></th>
              <th><%=GlobalVariable.DEAL_NUMBER_BUYER %></th>
              <th><%=GlobalVariable.DEAL_REMAIN_TIME %></th>
              <th><%=GlobalVariable.DEAL_UPDATE_TIME %></th>
              <th><%=GlobalVariable.STATUS %></th>
              <th><%=GlobalVariable.DEAL_REMOVE %></th>
          </tr>
          <%
              for (Deal deal : listSubDeals) {
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
                  <a class="done" id="updateLink<%=deal.getId() %>" name="updateLink<%=deal.getId() %>" href="" style="display: none;"><%=GlobalVariable.UPDATE %></a>
              </td>
              <%-- <td><%=Status.values()[deal.getStatus()] %></td> --%>
              <%
              String statusRemove = "";
              statusRemove = (deal.getStatus() != Status.DELETED.ordinal())?(GlobalVariable.DEAL_REMOVE):(GlobalVariable.DEAL_RESTORE);
              %>
              <td>
                <a class="done" href="/remove?id=<%=deal.getId() %>&opt=<%=statusRemove %>"><%=statusRemove %></a>
              </td>
          </tr>
          <%
              }
          %>
      </table>
      <!-- Paginator -->
      <div class="paginator" align="center">
              <%
              if (currentPage != 1) {
                  %>
                  <a href="dealmanager.app?currentPage=<%=currentPage - 1 %>">&lt;<%=GlobalVariable.PREVIOUS %></a> |
                  <%
              } else {
                  %>
                  <span class="disabled">&lt;<%=GlobalVariable.PREVIOUS %></span> |
                  <%
              }
              int count = deals.size();
              int pageCount = (int)Math.ceil((double)count / GlobalVariable.DEAL_PER_PAGE_DEALMANAGER);
              if (pageCount <= GlobalVariable.MAX_PAGE) {
                  for (int i = 0; i < pageCount; i++) {
                      if (currentPage != i + 1) {
                        %>
                        <a href="dealmanager.app?currentPage=<%=i + 1 %>"><%=i + 1 %></a> |
                        <% 
                      } else {
                        %><%=i + 1%> |<%
                      }
                  }
              } else {
                  %>
                  <a href="dealmanager.app?currentPage=<%=1 %>"><%=1 %></a> |
                  <a href="dealmanager.app?currentPage=<%=2 %>"><%=2 %></a> |
                  <a href="dealmanager.app?currentPage=<%=3 %>"><%=3 %></a> |
                  ...
                  | <a href="dealmanager.app?currentPage=<%=pageCount - 2 %>"><%=pageCount - 2 %></a>
                  | <a href="dealmanager.app?currentPage=<%=pageCount - 1 %>"><%=pageCount - 1 %></a>
                  | <a href="dealmanager.app?currentPage=<%=pageCount %>"><%=pageCount %></a>
                  <%
              }
              
              if (currentPage != pageCount) {
                  %>
                  <a href="dealmanager.app?currentPage=<%=currentPage + 1 %>"><%=GlobalVariable.NEXT %>&gt;</a>
                  <%
              } else {
                  %>
                  <span class="disabled"><%=GlobalVariable.NEXT %>&gt;</span>
                  <%
              }
              %>
          </div>
    </form>
  
    <hr/>
    <!-- Inser new Deal -->
    <div class="main" style="display: none;">
        <div class="headline"><%=GlobalVariable.DEAL_NEW %></div>
        <form action="/dealmanager" method="post" accept-charset="utf-8">
            <table>
                <tr>
                    <td><label for="title"><%=GlobalVariable.DEAL_TITLE %></label></td>
                    <td><input type="text" name="title" id="title" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="description"><%=GlobalVariable.DEAL_DESCRIPTION %></label></td>
                    <td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
                </tr>
                <tr>
                    <td><label for="address"><%=GlobalVariable.DEAL_ADDRESS %></label></td>
                    <td><input type="text" name="address" id="address" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="link"><%=GlobalVariable.DEAL_LINK %></label></td>
                    <td><input type="url" name="link" id="link" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="imageLink"><%=GlobalVariable.DEAL_IMAGE %></label></td>
                    <td>
                        <input type="url" name="imageLink" id="imageLink" size="65"/>
                        <input type="button" name="btnUpload" id="btnUpload" value="Upload" onclick="validateField()" />
                        <img id="imgLoad" src=''>
                    </td>
                </tr>
                <tr>
                    <td><label for="price"><%=GlobalVariable.DEAL_PRICE %></label></td>
                    <td>
                        <input name="price" id="price" type="text" autocomplete="off"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="basicPrice"><%=GlobalVariable.DEAL_BASIC_PRICE %></label></td>
                    <td>
                        <input name="basicPrice" id="basicPrice" type="text" autocomplete="off"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="unitPrice"><%=GlobalVariable.DEAL_UNIT_PRICE %>vị</label></td>
                    <td><input type="text" name="unitPrice" id="unitPrice" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="save"><%=GlobalVariable.DEAL_SAVE %></label></td>
                    <td>
                        <input name="save" id="save" type="text" autocomplete="off"
                                onfocusout="ConvertPriceText(this.value)" 
                                onblur="ConvertPriceText(this.value)"
                                onkeyup="this.value=FormatNumber(this.value);" />
                    </td>
                </tr>
                <tr>
                    <td><label for="numberBuyer"><%=GlobalVariable.DEAL_NUMBER_BUYER %></label></td>
                    <td><input type="number" name="numberBuyer" id="numberBuyer" size="65"/></td>
                </tr>
                <tr>
                    <td><label for="remainTime"><%=GlobalVariable.DEAL_REMAIN_TIME %></label></td>
                    <td><input type="text" name="remainTime" id="remainTime" size="65" value="00:00:00"/></td>
                </tr>
                <tr>
                    <td><label for="isVoucher"><%=GlobalVariable.DEAL_METHOD %></label></td>
                    <td><input type="checkbox" name="isVoucher" id="isVoucher" size="65"/>Giao voucher</td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
