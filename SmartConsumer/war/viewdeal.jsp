<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="viewdeal">
  <bean:write name="deal" property="title"/>
  <bean:write name="deal" property="description"/>
  <bean:write name="deal" property="price"/>
  <bean:write name="deal" property="basicPrice"/>
  <bean:write name="deal" property="address"/>
  
</div>