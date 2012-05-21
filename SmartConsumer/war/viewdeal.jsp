<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<%@ page import="com.gae.java.smartconsumer.util.*" %>
<%@ page import="java.util.List" %>

<html>
<head>
   <link href="css/default.css" rel="Stylesheet" type="text/css" />
   <link href="css/all.min.css" media="screen" rel="stylesheet" type="text/css">
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title><bean:write name="deal" property="title"/></title>
</head>
<body data-rendering="true">
    <div id="wrapper">
        <div id="container" class="container_12">
            <div class="widget_container grid_12 t_space_bottom" id="container_1">
                <div class="widget" id="container_1_1">
                </div>
            </div>
            <div class="widget_container grid_12 t_space_bottom" id="container_2"></div>
            <div class="widget_container grid_12 t_space_bottom" id="container_3">
                <div class="widdget_container grid_9 alpha" id="container_4">
                    <div class="widget" id="container_4_1">
                        
                    </div>
                    <div class="widget" id="container_4_2">
                        
                    </div>
                    <div class="t_news_details">
                        <div class="t_news_details_title"><bean:write name="deal" property="title"/></div>
                        <cite>
                            <bean:write name="deal" property="updateDate"/> 
                        </cite>
                        <div class="description">
                            <p>
                                <form type="POST" action="viewdeal.jsp">
                                  <select name="item" style="display: none;">
                                    <option><bean:write name="deal" property="id"/>
                                  </select>
                                  <br />
                                  <label>Số lượng mua: </label>

                                  <INPUT TYPE=submit name="submit" value="add">
                                  <INPUT TYPE=submit name="submit" value="remove">
                                </form>
                                
       
                            </p>
                            <div class="t_news_details_all"><div class="t_news_details_price"><bean:write name="price"/>
                            Trị giá <bean:write name="deal" property="basicPrice"/></div>
                            <div class="t_news_details_save">Tiết kiệm: <bean:write name="deal" property="save"/>%</div>
                            <div class="t_news_details_save">Số lượng đã mua: <bean:write name="deal" property="numberBuyer"/></div>
                            
                            </div>
                            <div class="t_news_details_image"><img width="100%" height="100%" src="<bean:write name="deal" property="imageLink"/>"/></div>
                            <div class="t_news_details_timeremain">Thời gian còn lại: <bean:write name="remainTime"/></div>
                            <div class="t_news_details_address"><bean:write name="deal" property="address"/></div>
                            
                        </div>
                        <div class="t_news_details_content">
                            <p><bean:write name="deal" property="description"/></p>
                        </div>
                    </div>
                    <div class="widget" id="container_4_3"></div>
                    <div class="widget" id="container_4_4"></div>
                    <div class="widget" id="container_4_5">
                        
                    </div>
                    <div class="widget" id="container_4_6">
                        
                    </div>
                </div>
                <div class="widdget_container grid_3 omega" id="container_5">
                    <div class="widget" id="container_5_1">
                        <div class="t_news_newer">
                            <h2>Giỏ hàng</h2>
                            <jsp:useBean id="cart" scope="session" class="com.gae.java.smartconsumer.action.DummyCart" />

                            <jsp:setProperty name="cart" property="*" />
                            <%
                                cart.processRequest(request);
                            %>
                            
                            <form type="POST" action="order.app">
                              <FONT size=5 COLOR="#CC0000">
                                <ol>
                                  <%
                                      String[] items = cart.getItems();
                                      for (int i = 0; i < items.length; i++) {
                                          //out.print(items[i]);
                                  %>
                                  <li>
                                    <%
                                        out.print(com.gae.java.smartconsumer.util.HTMLFilter.filter(items[i]));
                                    
                                   }
                               %>
                                  
                                </ol>
                              
                              </FONT>
                              <INPUT TYPE=submit name="submit" value="order">
                            </form>
                        </div>
                    </div>
                <div class="widget" id="container_5_2"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        
    </div>  </div>
    <script>
        $("div").filter(".control, .panel, .clear").remove();
        $("h3").remove();
        $("div").css({"border":"none"});
    </script>
    
</body>


<html>
  