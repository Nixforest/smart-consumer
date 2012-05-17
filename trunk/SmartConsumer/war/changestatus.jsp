<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:form action="/changestatusdone.app" method="POST">
    <table>
      <tr>
        <td>Trạng thái</td>
        <td>
            <input type="hidden" name="id" value="<bean:write name="deal" property="id" />" />
          <select name="status">
              <bean:define id="checkstatus" name="deal" property="status" />
              <option value="0" <% if(checkstatus.toString() == "0"){out.print("selected='selected'");} %>>Đang bán</option>
              <option value="1" <% if(checkstatus.toString() == "1"){out.print("selected='selected'");} %>>Hết hạn</option>
              <option value="2" <% if(checkstatus.toString() == "2"){out.print("selected='selected'");} %>>Đã xóa</option>
              <option value="3" <% if(checkstatus.toString() == "3"){out.print("selected='selected'");} %>>Chưa duyệt</option>
          </select>
      </td>
      <tr>
        <td colspan="1"><input type="submit" value="submit" /></td>
      </tr>
    </table>
</html:form>