
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<center>
<table>
    <tr>
        <td width="100px" align="left">STT</td>
        <td width="300px" align="left">Khach hang</td>        
        <td width="200px" align="left">Tuy chon</td>
    </tr>
    
    <logic:iterate id="element" name="listCustomer" indexId="index">
    <tr>
        <td><%=index.intValue() + 1%></td>        
        <td><a href="/viewbill.app?customerid=<bean:write name="element" property="id" />"><bean:write name="element" property="name" /></a></td>        
        <td><a href="/deletebill.app?customerid=<bean:write name="element" property="id" />">Hoan thanh</a></td>        
    </tr>
    </logic:iterate>
    
</table>
</center>