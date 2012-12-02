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
        ],
        mapOptions : {
            zoom : 12,
            mapTypeId : google.maps.MapTypeId.ROADMAP,
            navigationControl: true,
            navigationControlOptions: {
                style: google.maps.NavigationControlStyle.DEFAULT
            }
        }
    },

    onMapMaprender: function(map, gmap, options) {
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
		            me.openInfoWindow(markers[i], gmap, records[i].data, i, me._geo);
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
    
    openInfoWindow: function(marker, map, data, index, currentGeo) {
		var me = this;
		var content = ["<table>",
					        "<tr>",
					        "<td colspan=\"2\">",
					        "<span style='font-size: 12px;font-weight: normal;'><a href='" + data.link + "'>" + data.title + "</a></span>",
					        "</td>",
					    "</tr>",
					    "<tr>",
					        "<td>",
					            "<img src=\""+ data.linkImage + "\" width=\"100\" height=\"100\" style='border: 1px solid #333;padding: 1px;margin-right: 3px;' />",
					        "</td>",
					        "<td>",
					            "<span style='font-size: 12px;font-weight: normal;'>Giá: " + data.price + "</span> <br/>",
					            "<span style='font-size: 12px;font-weight: normal;'>Giá gốc: " + data.basicPrice + "</span> <br/>",
					            "<span style='font-size: 12px;font-weight: normal;'>Thời gian còn lại: " + data.remainTime,
					        "</span> </td>",
					"</tr>",
					"</table>",
					"<input type='button' value='Chỉ đường tới đây' id='directionButton"+index+"' />"].join("");
		var infowindow = new google.maps.InfoWindow();
		google.maps.event.addListener(marker, 'click', function(event) {
			infowindow.setContent(content);
			infowindow.open(map, marker);
		});
		google.maps.event.addListener(infowindow, 'domready', function() {
			$('#directionButton'+index).click(function(){
				me.showDirection(data.latitude, data.longitude, currentGeo, map)
			});
		});
	},
	
	showDirection: function(latitude, longitude, geo, map) {
		var currentLatitude = geo.getLatitude();
		var currentLongitude = geo.getLongitude();
		
		//direction
		var directionsDisplay = new google.maps.DirectionsRenderer();
        var directionsService = new google.maps.DirectionsService();
        var start = new google.maps.LatLng(currentLatitude, currentLongitude);
        var end = new google.maps.LatLng(latitude, longitude);
        var request = {
		  origin: start,
		  destination: end,
		  travelMode: google.maps.TravelMode.DRIVING
		};
        
        directionsDisplay.setMap(map);
        directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			}
		});
	}
});