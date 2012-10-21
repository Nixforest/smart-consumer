<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib  uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-ui-1.8.20.custom/css/ui-lightness/jquery-ui-1.8.20.custom.css" />
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-ui-1.8.20.custom.min.js"></script>  
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom/js/jquery-ui-timepicker-addon.js"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
    <div id="lheader"><tiles:insert attribute="header"/></div>
    <div id="menu"><tiles:insert attribute="menu" /></div>
    <div id="lbody"><tiles:insert attribute="body"/></div>
    <div id="lfooter"><tiles:insert attribute="footer"/></div>
</body>
</html>
