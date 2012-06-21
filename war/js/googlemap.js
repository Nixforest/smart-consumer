 var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var geocoder;
var marker;
function initialize() {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var dhcntt = new google.maps.LatLng(10.8702244, 106.80345790000001);
  var myOptions = {
    zoom:15,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    center: dhcntt
  }
  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
  directionsDisplay.setMap(map);
}
function initialize1() {
	 geocoder = new google.maps.Geocoder();
	  directionsDisplay = new google.maps.DirectionsRenderer();
	  var dhcntt = new google.maps.LatLng(10.8702244, 106.80345790000001);
	  var myOptions = {
	    zoom:15,
	    mapTypeId: google.maps.MapTypeId.ROADMAP,
	    center: dhcntt
	  }
	  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	  directionsDisplay.setMap(map);
	}
function calcRoute() {
  var start = document.getElementById("start").value + ' , Hồ Chí Minh, Việt Nam';
  var end = document.getElementById("end").value+ ' , Hồ Chí Minh, Việt Nam';
  var request = {
    origin:start,
    destination:end,
    travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(result, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(result);
    }
  });
}
function calcRoute1() {
	  var start = document.getElementById("start").value + ' , Hồ Chí Minh, Việt Nam';
	  var end = $("#end option:selected").val() + ', Hồ Chí Minh, Việt Nam';
	  var request = {
	    origin:start,
	    destination:end,
	    travelMode: google.maps.TravelMode.DRIVING
	  };
	  directionsService.route(request, function(result, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(result);
	    }
	  });
	}

function codeAddress() {
	  if(marker != null){
	  /* map.removeOverlay(marker);*/
	  marker.setMap(null);

	  }
	    var address = $("#add option:selected").val() + ", Hồ Chí Minh, Việt Nam";
	    var infowindow = new google.maps.InfoWindow({
		    content: address
		});
		//call ajax to get add of provider
	    geocoder.geocode( { 'address': address}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
		/* alert(results[0].geometry.location.lat());
		  alert(results[0].geometry.location.lng());*/
	        map.setCenter(results[0].geometry.location);
			map.setZoom(16);
	        marker = new google.maps.Marker({
	            map: map, 
	            position: results[0].geometry.location
	        });
	        infowindow.setContent(address);
	        infowindow.open(map, marker);
	      } else {
	        alert("Geocode was not successful for the following reason: " + status);
	      }
	    });
	    google.maps.event.addListener(marker, 'click', function() {
	    	  infowindow.open(map,marker);
	    	});
	  }