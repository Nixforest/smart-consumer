<%@ page import="com.gae.java.smartconsumer.util.*" %>
<%@ page import="java.util.List" %> 

<%@ page import="com.gae.java.smartconsumer.util.*" %>

<%

Cart cart = (Cart) session.getAttribute("cart");
java.text.NumberFormat fmt = java.text.DecimalFormat.getInstance();
if (cart != null) {
	if(cart.getCart().size() != 0)
	{
		List<DealItem> list = cart.getCart();
		int index=0;
%>
<center>
	<h2>Thong Tin Gio Hang</h2>
	<table border=”1″ width=”100%”>
	<tr>
		<th width="300px" align="left">Ten san pham</th>		
		<th width="100px" align="left">So luong</th>	
		<th width="200px" align="left">Don gia</th>
		<th width="100px" align="left">Tien te</th>
		<th width="100px" align="left">Tuy chon</th>	
	</tr>
	<%
			for (DealItem d : list) {
	%>
	<tr>
		<td><%=d.getTitle()%></td>
		<td><%=d.getQuantity()%></td>	
		<td><%=d.getPrice()%></td>	
		<td><%=d.getUnitPrice()%></td>
		<td><a href="/delitem.app?index=<%=index %>" >Huy bo</a></td>		
	</tr>	
	<%
		index++;
	}%>		
	
	</table>
	</center>
	<h2 align="right">Tong Tien:<%=fmt.format(cart.sumMoney()) %> VND</h2>
	<form action="" method="post">
	<table border=”1″ width=”100%”>		
	<tr>
		<td>Ho Ten:</td>
		<td><input type="text" name="text_name"/></td>
	</tr>	
	<tr>
		<td>So Dien Thoai:</td>	
		<td><input type="text" name="text_phone"/></td>				
	</tr>
	<tr>
		<td>Dia Chi:</td>		
		<td><input type="text" name="text_address"/></td>			
	</tr>
	<tr>
		<td></td>		
		<td align="right"><input type="submit" name="sub_info"/></td>	
	</tr>
	</table>
	</form>
	<%}else{%>
	
	<center><h2>Ban chua chon san pham nao</h2></center>	
	<%	}
	}else{
	%>
		<center><h2>Ban chua chon san pham nao</h2></center>	
	<%}
	%>

