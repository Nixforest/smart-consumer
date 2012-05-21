<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<table border=”1″ width=”100%”>		
	<tr>
		<td>Ho Ten:</td>
		<td><bean:write name="billinfo" property="name"/></td>
	</tr>	
	<tr>
		<td>So Dien Thoai:</td>	
		<td><bean:write name="billinfo" property="phone"/></td>				
	</tr>
	<tr>
		<td>Dia Chi:</td>		
		<td><bean:write name="billinfo" property="address"/></td>			
	</tr>	
	</table>
	<a href="/deletebill.app?customerid=<bean:write name="customerid"/>">Hoan thanh</a>
	<%java.text.NumberFormat fmt = java.text.DecimalFormat.getInstance(); %>
 <center>
	<h2>Thong Tin Giao Dich</h2>
	<table border=”1″ width=”100%”>
	<tr>		
		<th width="300px" align="left">Ten san pham</th>		
		<th width="100px" align="left">So luong</th>	
		<th width="200px" align="left">Don gia</th>
		<th width="100px" align="left">Tien te</th>		
	</tr>
	 <logic:iterate id="element" name="list_cart" indexId="index">
	<tr>		
		<td><bean:write name="element" property="title"/></td>
		<td><bean:write name="element" property="quantity"/></td>	
		<td><bean:write name="element" property="price"/></td>	
		<td><bean:write name="element" property="unitPrice"/></td>		
	</tr>	
	</logic:iterate>
	</table>
	</center>
	<h2 align="right">Tong Tien:<bean:write name="money"/> VND</h2>