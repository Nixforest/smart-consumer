<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Test Page</title>
</head>
<body>
<%
String error = (String)request.getAttribute("error");
%>
    <form action="/test" method="post" accept-charset="utf-8"> 
      <label><%=error %></label>
      <input type="Submit" value="Submit"/>
    </form>
</body>
</html>
