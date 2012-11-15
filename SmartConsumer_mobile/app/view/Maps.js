Ext.define('SmartConsumer.view.Maps', {
    extend: 'Ext.Map',
    alias: 'widget.detallemapa',
    requires: [
           'SmartConsumer.store.Maps'    
    ],

    config: {
    	store: 'Maps',
    	useCurrentLocation: true,
        listeners: [
            {
                fn: 'onMapMaprender',
                event: 'maprender'
            }
        ]
    },

    onMapMaprender: function(map, gmap, options) {console.log('map', map);
    console.log('gmap', gmap);console.log('options', options);
    	//set zoom
    	gmap.zoom = 12;
        var shape = {
        		coord: [1, 1, 1, 20, 18, 20, 18 , 1],
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
		            me.openInfoWindow(markers[i], map, records[i].data.title);
		        }
		    }
		});
    },
    
    openInfoWindow: function(marker, map, data) {
		var infowindow = new google.maps.InfoWindow({
			content: "holding..."
		});
		
		google.maps.event.addListener(marker, 'click', function(event) {
			infowindow.setContent(data);
			infowindow.open(map, marker);
		});
		google.maps.event.addListener(marker, 'mouseover', function() {
			infowindow.close();
		});
	},
    
    setMarkers: function(map, locations) {
	  // Add markers to the map

	  // Marker sizes are expressed as a Size of X,Y
	  // where the origin of the image (0,0) is located
	  // in the top left of the image.

	  // Origins, anchor positions and coordinates of the marker
	  // increase in the X direction to the right and in
	  // the Y direction down.
	  var image = new google.maps.MarkerImage('images/beachflag.png',
	      // This marker is 20 pixels wide by 32 pixels tall.
	      new google.maps.Size(20, 32),
	      // The origin for this image is 0,0.
	      new google.maps.Point(0,0),
	      // The anchor for this image is the base of the flagpole at 0,32.
	      new google.maps.Point(0, 32));
	  var shadow = new google.maps.MarkerImage('images/beachflag_shadow.png',
	      // The shadow image is larger in the horizontal dimension
	      // while the position and offset are the same as for the main image.
	      new google.maps.Size(37, 32),
	      new google.maps.Point(0,0),
	      new google.maps.Point(0, 32));
	      // Shapes define the clickable region of the icon.
	      // The type defines an HTML <area> element 'poly' which
	      // traces out a polygon as a series of X,Y points. The final
	      // coordinate closes the poly by connecting to the first
	      // coordinate.
	  var shape = {
	      coord: [1, 1, 1, 20, 18, 20, 18 , 1],
	      type: 'poly'
	  };
	  for (var i = 0; i < locations.length; i++) {
	    var beach = locations[i];
	    var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
	    var marker = new google.maps.Marker({
	        position: myLatLng,
	        map: map,
	        shadow: shadow,
	        icon: image,
	        shape: shape,
	        title: beach[0],
	        zIndex: beach[3]
	    });
	  }
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
 	}
});