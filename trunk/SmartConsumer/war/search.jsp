<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.gae.java.smartconsumer.util.GlobalVariable" %>
<form action="/searchdeal.app" method="post">
  <div class="searchDeal">
      <ul>
          <li><%=GlobalVariable.SEARCH %></li>
          <li><%=GlobalVariable.PRICE_FROM %>  
              <select name="searchPriceFrom">
                  <option value="0">0</option>
                  <option value="50">50</option>
                  <option value="100">100</option>
                  <option value="150">150</option>
                  <option value="200">200</option>
                  <option value="250">250</option>
              </select>
          </li>
          <li><%=GlobalVariable.PRICE_TO %>
              <select name="searchPriceTo">
                  <option value="50">50</option>
                  <option value="100">100</option>
                  <option value="150">150</option>
                  <option value="200">200</option>
                  <option value="250">250</option>
                  <option value="300">300</option>
              </select>
          </li>
          <li>
              <input type="submit" value="<%=GlobalVariable.SEARCH %>" />
          </li>
      </ul>
  </div>
</form>