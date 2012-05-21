<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@page import="com.gae.java.smartconsumer.model.Deal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil"%>

<%@page import="com.gae.java.smartconsumer.util.Status"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>Xem bản đồ</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<style type="text/css">
html {
  height: 100%
}

body {
  height: 100%;
  margin: 0;
  padding: 0
}

#map {
  height: 100%
}
</style>
<script type="text/javascript"
  src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAf3qvpvrX05JezFZLUxRlPdSFvdIQYJ3A&sensor=true">
      </script>

<script type="text/javascript">
	var geocoder;
	var map;
	var side_bar_html = "";
    var gmarkers = [];
    var htmls = [];
    //var i = 0;
    var clicknumber = 0;
    
	function initialize() {
		var lat = 10.819143;
        var lng = 106.631927;
		geocoder = new google.maps.Geocoder();
	    var latlng = new google.maps.LatLng(lat, lng);
	    var myOptions = {
	    	    zoom: 12,
	    	    center: latlng,
	    	    mapTypeId: google.maps.MapTypeId.ROADMAP
	    }
	    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	    
        setAddressFromDatabaseX();
	}
	function codeAddress() {
		var address = document.getElementById("address").value;
		codeAddressWithParam(address, "Vị trí của bạn");
	}
	function codeAddressWithParam(address, title, image) {
		geocoder.geocode(
                {'address': address},
                function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        //map.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: map,
                            draggable: true,
                            animation: google.maps.Animation.DROP,
                            position: results[0].geometry.location,
                            title: address
                        });
                        var infowindow = new google.maps.InfoWindow({
                        	content: address 
                        });
                        google.maps.event.addListener(marker, 'mouseover', function() {
                        	infowindow.open(map, marker);
                        });google.maps.event.addListener(marker, 'mouseout', function() {
                            infowindow.close();
                        });
                    } else {
                        //alert("Geocode was not successful for the following reason: " + status);
                        return;
                    }
                });
	}
	function codeAddressWithIndex(index) {
		var address = document.getElementById("address" + index).innerText;
        var title = document.getElementById("title" + index).innerText;
        var image = document.getElementById("image" + index).innerText;
        var price = document.getElementById("price" + index).innerText;
        var basicPrice = document.getElementById("basicPrice" + index).innerText;
        var numberBuyer = document.getElementById("numberBuyer" + index).innerText;
        var remainTime = document.getElementById("remainTime" + index).innerText;
        geocoder.geocode(
                {'address': address},
                function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        //map.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: map,
                            draggable: true,
                            animation: google.maps.Animation.DROP,
                            position: results[0].geometry.location,
                            title: address
                        });
                        var html = "<table>" +
                                            "<tr>" +
                                            "<td colspan=\"2\">" +
                                            "<b>" + index + ". " + title + "</b>"
                                            + "</td>"
                                        + "</tr>" +
                                        "<tr>" +
                                            "<td>" +
                                                "<img src=\""+ image + "\" width=\"100\" height=\"100\"></img>"
                                            + "</td>" +
                                            "<td>" +
                                                "<b>Giá: </b>" + price + "<br/>" +
                                                "<b>Giá gốc: </b>" + basicPrice + "<br/>" +
                                                "<b>Số lượng đã mua: </b>" + numberBuyer + "<br/>" +
                                                "<b>Thời gian còn lại: </b>" + remainTime
                                            + "</td>"
                                    + "</tr>"
                                    + "</table>";
                        var infowindow = new google.maps.InfoWindow({
                            content: html
                        });
                        google.maps.event.addListener(marker, 'mouseover', function() {
                            infowindow.open(map, marker);
                        });google.maps.event.addListener(marker, 'mouseout', function() {
                            infowindow.close();
                        });

                        gmarkers[index] = marker;
                        htmls[index] = infowindow;
                    } else {
                        //alert("Geocode was not successful for the following reason: " + status);
                        return;
                    }
                });
    }
    function myclick(i) {
    	htmls[i].open(map, gmarkers[i]);
    	//gmarkers[i].openInfoWindowHtml("sdfsd");
    }
	function setAddressFromDatabase() {
		var numberOfDeal = document.getElementById("numberOfDeal").innerText;
		clicknumber++;
		start = (clicknumber - 1) * 10;
		end = (clicknumber - 1) * 10 + 10;
		if (end > numberOfDeal) {
			end = numberOfDeal
		}
		for (var i = start; i < end; i++) {
            codeAddressWithIndex(i);
        }
	}
	function setAddressFromDatabaseX() {
        var numberOfDeal = document.getElementById("numberOfDeal").innerText;
        for (var i = 0; i < numberOfDeal; i++) {
            codeAddressWithIndex(i);
        }
    }
	function setAddressFromDatabaseRevert() {
        var numberOfDeal = document.getElementById("numberOfDeal").innerText;
        for (var i = numberOfDeal - 1; i >= 0; i--) {
            codeAddressWithIndex(i);
        }
    }
	
	function showLocation(position) {
		  var latitude = position.coords.latitude;
		  var longitude = position.coords.longitude;
          var latlng = new google.maps.LatLng(latitude, longitude);
		  var marker = new google.maps.Marker({
              map: map,
              draggable: true,
              animation: google.maps.Animation.DROP,
              position: latlng,
              title: "Vị trí của bạn"
          });
		  map.setCenter(latlng);
	}

	function errorHandler(err) {
	  if(err.code == 1) {
	    alert("Error: Access is denied!");
	  }else if( err.code == 2) {
	    alert("Error: Position is unavailable!");
	  }
	}
	function getLocation(){

	   if(navigator.geolocation){
	      // timeout at 60000 milliseconds (60 seconds)
	      var options = {timeout:60000};
	      navigator.geolocation.getCurrentPosition(showLocation, 
	                                               errorHandler,
	                                               options);
	   }else{
	      alert("Sorry, browser does not support geolocation!");
	   }
	}
</script>

</head>
<body onload="initialize()">
  <%
  String url = "";
  String urlLinktext = "";
  String nickName = "";
  
  if (request.getAttribute("url") != null) {
      url = (String)request.getAttribute("url");
  }
  if (request.getAttribute("urlLinktext") != null) {
      urlLinktext = (String)request.getAttribute("urlLinktext");
  }
  if (request.getAttribute("nickName") != null) {
      nickName = (String)request.getAttribute("nickName");
  }
  List<Deal> deals = (List<Deal>)request.getAttribute("listDeals");
  %>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      <div style="float: left;">
        <img src="images/smartconsumer.png" />
      </div>
      <div style="float: left;" class="headline">Xem bản đồ</div>
      <div style="float: right;">
        <a href="<%=url%>"><%=urlLinktext%></a>
        <%=(urlLinktext.equals("Login") ? "" : nickName)%></div>
    </div>
  </div>
  <div style="clear: both;"></div>
  <table>
    <tr>
        <td><div id="map_canvas" style="width: 750px; height: 480px;"></div></td>
        <td>
            <div id="side_bar" style="float:right;height:480px;overflow: scroll;">
                <%
                int i = -1;
                for (Deal item : deals) {
                    i++;
                    %>
                    <a class="titlelinkmap" href="javascript:myclick(<%=i %>)"><%=item.getTitle() %></a>
                    <br/>
                    <%
                }
                %>
            </div>
        </td>
    </tr>
  </table>
  
  <div>
    <form>
      <input id="address" type="textbox" value="Thành Phố Hồ Chí Minh" size="100"> <input type="button"
        value="Vị trí của bạn" onclick="getLocation();"> <input type="button" value="Tìm địa điểm"
          onclick="codeAddress()">
          <input type="button" value="Load dữ liệu" onclick="setAddressFromDatabase()">
          
          <input type="button" value="Load ngược" onclick="setAddressFromDatabaseRevert()">
    </form>

  </div>
  <div style="display: none;">
    <label id="numberOfDeal" name="numberOfDeal"><%=deals.size() %></label>
    <table>
      <%
    i = -1;
    for (Deal item : deals) {
        i++;
        %>
      <tr>
        <td><label id="id<%=i %>" name="id<%=i %>"><%=i %></label></td>
        <td><label id="title<%=i %>" name="title<%=i %>"><%=item.getTitle() %></label></td>
        <td><label id="image<%=i %>" name="image<%=i %>"><%=item.getImageLink() %></label></td>
        <td><label id="price<%=i %>" name="price<%=i %>"><%=GeneralUtil.convertPriceToText(item.getPrice()) %></label>
        </td>
        <td><label id="basicPrice<%=i %>" name="basicPrice<%=i %>"><%=GeneralUtil.convertPriceToText(item.getBasicPrice()) %></label>
        </td>
        <td><label id="numberBuyer<%=i %>" name="numberBuyer<%=i %>"><%=item.getNumberBuyer() %></label></td>
        <td><label id="remainTime<%=i %>" name="remainTime<%=i %>"><%=GeneralUtil.getRemainTime(item.getEndTime()) %></label>
        </td>
        <td><label id="address<%=i %>" name="address<%=i %>"><%=item.getAddress() %></label></td>
      </tr>
      <%
    }
    %>
    </table>
  </div>
</body>
</html>
