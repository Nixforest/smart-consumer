<!-- File view use to change status of Deal -->

<%@page import="com.gae.java.smartconsumer.util.GlobalVariable"%>
<%@page import="com.gae.java.smartconsumer.util.Status"%>
<%@page import="com.gae.java.smartconsumer.util.GlobalVariable" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:form action="/changestatusdone.app" method="POST">
    <table>
      <tr>
        <td><%=GlobalVariable.STATUS %></td>
        <td>
            <input type="hidden" name="id" value="<bean:write name="deal" property="id" />"  />
          <select name="status">
              <bean:define id="checkstatus" name="deal" property="status" />
              <%
              for (int i = 0; i < Status.values().length; i++) {
                  %>
                  <option value="<%=i %>" <%if(checkstatus.equals(i)) out.print("selected='selected'"); %>><%=Status.values()[i]%></option>
                  <%
              }
              %>
          </select>
      </td>
      <tr>
        <td colspan="1"><input type="submit" value="submit" /></td>
      </tr>
    </table>
</html:form>