<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@page import="com.gae.java.smartconsumer.model.Deal" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gae.java.smartconsumer.util.GeneralUtil" %>

<%@page import="com.gae.java.smartconsumer.util.Status"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Xem bản đồ</title>
<script src="//maps.google.com/maps?file=api&amp;v=2.x&amp;" type="text/javascript"></script>
<!-- <script src="http://maps.google.com/maps?file=api&v=2" type="text/javascript"></script> -->

<script type="text/javascript">
	//         
    var map = null;
	var geocoder = null;
	function initialize() {
		if (GBrowserIsCompatible()) {
            var lat = 10.819143;
            var lng = 106.631927;
			map = new GMap2(document.getElementById("map"));
            map.addControl(new GSmallMapControl());
            map.addControl(new GMapTypeControl());
			map.setCenter(new GLatLng(lat, lng), 13);
			geocoder = new GClientGeocoder();
			setAddressInDatabase();
		}
	}
	function showAddress(address) {
		//document.write(document.getElementById("numberOfDeal"));
		  geocoder.getLatLng(
		    address,
		    function(point) {
		      if (!point) {
		        //alert(address + " not found");
		        //return;
		      } else {
		        //map.setCenter(point, 13);
		        var marker = new GMarker(point);
                GEvent.addListener(marker, "click", function() {
                    marker.openInfoWindowTabsHtml(address);
                });
		        map.addOverlay(marker);

		        // As this is user-generated content, we display it as
		        // text rather than HTML to reduce XSS vulnerabilities.
		        //marker.openInfoWindow(document.createTextNode(address));
		      }
		    }
		  );
	}
	function setAddressInDatabase() {		
		for (var i = document.getElementById("numberOfDeal").innerText - 1; i >= 0 ; i--) {
			var address = "address" + i;
			showAddress(document.getElementById(address).innerText);
		}
	}
	/* function load() {
		if (GBrowserIsCompatible()) {
			var lat = 10.819143;
			var lng = 106.631927;

			var map = new GMap2(document.getElementById("map"));
			map.addControl(new GSmallMapControl());
			map.addControl(new GMapTypeControl());
			//map.setCenter(new GLatLng(lat, lng), 15);
			
            map.setCenter(new GLatLng(37.4419, -122.1419), 13);

			var infoTabs = [
					new GInfoWindowTab("Address", "Thành phố Hồ chí minh<br /> Việt Nam"), ];

			// Place a marker in the center of the map and open the info window
			GDownloadUrl("address.xml", function(data, responseCode) {
				var xml = GXml.parse(data);
				var markers = xml.documentElement.getElementsByTagName("marker");
				for (var i = 0; i < markers.length; i++) {
					var point = new GLatLng(parseFloat(markers[i].getAttribute("lat")),
							 parseFloat(markers[i].getAttribute("lng")));
					map.addOverlay(new GMarker(point));
				}
			});
			var marker = new GMarker(map.getCenter());
			GEvent.addListener(marker, "click", function() {
				marker.openInfoWindowTabsHtml(infoTabs);
			});
			map.addOverlay(marker);
			marker.openInfoWindowTabsHtml(infoTabs);

			var point = new GLatLng(lat, lng);
			panoramaOptions = {
				latlng : point
			};
			pano = new GStreetviewPanorama(document
					.getElementById("streetview"), panoramaOptions);
			GEvent.addListener(pano);

		}
	}

	function readData() {
		var request = GXmlHttp.create();
		request.open("GET", "address.xml", true);
		request.onreadystatechange = function() {
		  if (request.readyState == 4) {
		    alert(request.responseText);
		  }
		}
		request.send(null);
	} */
</script>

</head>
<body onload="initialize()" onunload="GUnload()">
  <%
  List<Deal> deals = (List<Deal>) (request.getAttribute("listDeals"));
  %>
    <form action="#" onsubmit="showAddress(this.address.value); return false">
        <input type="text" size="60" name="address"></input>
        <input type="submit" value="Go!"/>
        <div id="map" style="width: 500px; height: 400px"></div>
    <div style="clear: both;"></div>
    Number of Deals: <label id="numberOfDeal" name="numberOfDeal"><%=deals.size() %></label>
    [<a href="/getdeal">Cập nhật</a>]
    [<a href="/removeall">Xóa tất cả</a>]
    <table>
        <tr>
            <th>Id</th>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Địa chỉ</th>
            <th>Link</th>
            <th>Giá</th>
            <th>Giá gốc</th>
            <th>Tiết kiệm</th>
            <th>Người mua</th>
            <th>Thời gian</th>
            <th>Cập nhật</th>
            <th>Status</th>
            <th>Remove</th>
        </tr>
        <%
            int i = -1; 
            for (Deal deal : deals) {
                //deal = GeneralUtil.decodeDeal(deal);
                i++;
        %>
        <tr>
            <td><%=deal.getId() %></td>
            <td><%=deal.getTitle() %></td>
            <td><%=deal.getDescription() %></td>
            <td><label id="address<%=i%>" name="address<%=i%>"><%=deal.getAddress() %></label></td>
            <td><%=deal.getLink() %></td>
            <td><%=deal.getPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getBasicPrice() + " " + deal.getUnitPrice() %></td>
            <td><%=deal.getSave() + "%" %></td>
            <td><%=deal.getNumberBuyer() %></td>
            <td><%=GeneralUtil.getRemainTime(deal.getEndTime()) %></td>
            <%-- <td><%=deal.isVoucher() %></td> --%>
            <td><%=deal.getUpdateDate()%></td>
            <td><%=Status.values()[deal.getStatus()] %></td>
            <%
            String statusRemove = "";
            statusRemove = (deal.getStatus() != Status.DELETED.ordinal())?("Remove"):("Restore");
            %>
            <td>
              <a class="done" href="/remove?id=<%=deal.getId() %>&opt=<%=statusRemove %>"><%=statusRemove %></a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
  
    </form>
    <hr/>
  <!-- <input type="submit" value="LoadData" onclick="readData()"/> -->
</body>
</html>
