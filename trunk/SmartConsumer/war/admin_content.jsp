<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<tiles:insert definition="layout" flush="true">
  <tiles:put name="body" value="/getdeal.jsp" />
</tiles:insert>