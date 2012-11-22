Ext.define('SmartConsumer.view.map.MapDeal', {
    extend: 'Ext.Map',
    xtype: 'mapdeal',
    requires: [
           'SmartConsumer.store.Maps'    
    ],

    config: {
    	store: 'Maps',
    	useCurrentLocation: {
    		autoUpdate: false
    	},
    	preventZooming: false,
    	fullscreen: false,
    	radius: null,
        listeners: [
            {
                fn: 'onMapMaprender',
                event: 'maprender'
            }
        ]
    },

    onMapMaprender: function(map, gmap, options) {
    	//set zoom
    	gmap.zoom = 12;
        var shape = {
        		coord: [0, 0, 20, 0, 20, 20, 0, 20],
        		type: 'poly'
        };
		var infowindow = new google.maps.InfoWindow({
			content: "holding..."
		});
		var me = this;
		var store = Ext.create('SmartConsumer.store.Maps');
		store.load({
		    callback: function(records, operation, success) {
		    	var markers = Array();
		    	for (var i = 0; i < records.length; i++) {
		            var m = new google.maps.LatLng(records[i].data.latitude, records[i].data.longitude);

		            var marker = new google.maps.Marker({
		                position: m,
		                map: gmap,
		                draggable: false,
		                animation: google.maps.Animation.DROP,
		                shape: shape
		            });
		            markers.push(marker);
		            me.openInfoWindow(markers[i], map, records[i].data);
		        }
		    }
		});
		var currentLocation = new google.maps.LatLng(this._geo.getLatitude(), this._geo.getLongitude());
		var iconCurrentPosition = "resources/images/currentPosition.png";
		var currentPositionMarker = new google.maps.Marker({
							            position: currentLocation,
							            map: gmap,
							            draggable: false,
							            animation: google.maps.Animation.DROP,
							            shape: shape,
							            icon: iconCurrentPosition
							        });
		//this.setRadius(100);
		var radius = this.getRadius();
		var myCity = new google.maps.Circle({
			  center: currentLocation,
			  radius: radius,
			  strokeColor: "#0000FF",
			  strokeOpacity: 0.8,
			  strokeWeight: 2,
			  fillColor: "#0000FF",
			  fillOpacity: 0.4
	    });

		myCity.setMap(gmap);
    },
    
    openInfoWindow: function(marker, map, data) {
		var infowindow = new google.maps.InfoWindow({
			content: "holding..."
		});
		
		var content = "<table>" +
					        "<tr>" +
					        "<td colspan=\"2\">" +
					        "<span style='font-size: 12px;font-weight: normal;'><a href='" + data.link + "'>" + data.title + "</a></span>"
					        + "</td>"
					    + "</tr>" +
					    "<tr>" +
					        "<td>" +
					            "<img src=\""+ data.linkImage + "\" width=\"100\" height=\"100\" style='border: 1px solid #333;padding: 1px;margin-right: 3px;' />"
					        + "</td>" +
					        "<td>" +
					            "<span style='font-size: 12px;font-weight: normal;'>Giá: " + data.price + "</span> <br/>" +
					            "<span style='font-size: 12px;font-weight: normal;'>Giá gốc: " + data.basicPrice + "</span> <br/>" +
					            "<span style='font-size: 12px;font-weight: normal;'>Thời gian còn lại: " + data.remainTime
					        + "</span> </td>"
					+ "</tr>"
					+ "</table>";
		
		google.maps.event.addListener(marker, 'click', function(event) {
			infowindow.setContent(content);
			infowindow.open(map, marker);
		});
		google.maps.event.addListener(marker, 'closeclick', function() {
			infowindow.close();
		});
	},
	
	setMarker: function(position) {
		
	},
    
    getLocation: function() {
 	   if(navigator.geolocation){
 	      // timeout at 60000 milliseconds (60 seconds)
 	      var options = {timeout:60000};
 	      navigator.geolocation.getCurrentPosition(showLocation, 
 	                                               errorHandler,
 	                                               options);
 	   }else{
 	      alert("Sorry, browser does not support geolocation!");
 	   }
 	},
 	
 	doDrawCircle: function(map, circle){
 		/*if (circle) {
 			map.removeOverlay(circle);
 		}*/


 		if (centerMarker) {
 			map.setCenter(centerMarker.getLatLng())
 		}
 		else {
 			centerMarker = new GMarker(map.getCenter(),{draggable:true});
 			GEvent.addListener(centerMarker,'dragend',drawCircle)
 			map.addOverlay(centerMarker);
 		}

 		var center = map.getCenter();

 		var bounds = new GLatLngBounds();

 		
 		var circlePoints = Array();

 		with (Math) {
 			if (circleUnits == 'KM') {
 				var d = circleRadius/6378.8;	// radians
 			}
 			else { //miles
 				var d = circleRadius/3963.189;	// radians
 			}

 			var lat1 = (PI/180)* center.lat(); // radians
 			var lng1 = (PI/180)* center.lng(); // radians

 			for (var a = 0 ; a < 361 ; a++ ) {
 				var tc = (PI/180)*a;
 				var y = asin(sin(lat1)*cos(d)+cos(lat1)*sin(d)*cos(tc));
 				var dlng = atan2(sin(tc)*sin(d)*cos(lat1),cos(d)-sin(lat1)*sin(y));
 				var x = ((lng1-dlng+PI) % (2*PI)) - PI ; // MOD function
 				var point = new GLatLng(parseFloat(y*(180/PI)),parseFloat(x*(180/PI)));
 				circlePoints.push(point);
 				bounds.extend(point);
 			}

 			if (d < 1.5678565720686044) {
 				circle = new GPolygon(circlePoints, '#000000', 2, 1, '#000000', 0.25);	
 			}
 			else {
 				circle = new GPolygon(circlePoints, '#000000', 2, 1);	
 			}
 			map.addOverlay(circle); 

 			map.setZoom(map.getBoundsZoomLevel(bounds));
 		}
 	},
 	
 	drawCircle: function(rad) {
 		 
 	    rad *= 1600; // convert to meters if in miles
 	    /*if (draw_circle != null) {
 	        draw_circle.setMap(null);
 	    }
 	    draw_circle =*/ new google.maps.Circle({
 	        center: center,
 	        radius: rad,
 	        strokeColor: "#FF0000",
 	        strokeOpacity: 0.8,
 	        strokeWeight: 2,
 	        fillColor: "#FF0000",
 	        fillOpacity: 0.35,
 	        map: map
 	    });
 	}
});