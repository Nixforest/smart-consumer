Ext.define('SmartConsumer.controller.Maps', {
    extend: 'Ext.app.Controller',
   
    config: {
    	routes: {
			'map': 'onOpenMapDeal'
		},
    	mapDealView: null,
    },
    
    initialize: function() {
    },
    /*config: {
    	routes: {
			'map': 'onOpenMapDeal'
		},
		mapDealView: null,
		markers: [],
	    directionsDisplay: null,
	    directionsService: null,
		stores: 'Maps',
        refs: {
            mapViewPanel: 'mapViewPanel',
            MapDeal: '#MapDeal',
            mapDeal: 'mapDeal',
            radiusPicker: 'radiusPicker'
        },
        control: {
            mapViewPanel: {
            	dealSelectCommand: "onDealSelected"
            },
            mapDeal: {
                backButton: "onBackButton",
                mapRender: "onMapRender",
                nearButton: "onNear"
            },
            radiusPicker: {
                pickerChanged: "onPickerRadiusChange"
            }
        }
    },

    launch: function () {
        // Initialize Google Map Services
        this.getDirectionsDisplay = new google.maps.DirectionsRenderer();
        this.getDirectionsService = new google.maps.DirectionsService();

        var mapRendererOptions = {
            //draggable: true,  //Allows to drag route
            //hideRouteList: true,
            suppressMarkers: true
        };

        this.getDirectionsDisplay.setOptions(mapRendererOptions);
    },

    // Transitions
    slideLeftTransition: { type: 'slide', direction: 'left' },
    slideRightTransition: { type: 'slide', direction: 'right' },

    onDealSelected: function (list, record) {
        var mapView = this.getPetMap();
        mapView.setRecord(record);
        Ext.Viewport.animateActiveItem(mapView, this.slideLeftTransition);


        this.renderMap(mapView, mapView.down("#mapDeal").getMap(), record.data);
    },

    onBackButton: function () {
        var store = Ext.getStore('Maps');
        //store.getProxy().setUrl('http://nodetest-loutilities.rhcloud.com/dogtag/');
        store.load();
        Ext.Viewport.animateActiveItem(this.getMapViewPanel(), this.slideRightTransition);
    },

    renderMap: function (extmap, map, record) {
        // erase old markers
        if (this.markers.length > 0) {
            Ext.each(this.markers, function (marker) {
                marker.setMap(null);
            });
        } 
        var position = new google.maps.LatLng(record.latitude, record.longitude);

        var dynaMarker = new google.maps.Marker({
            position: position,
            title: record.name + "'s Location",
            map: map,
            icon: 'resources/images/yellow_MarkerB.png'
        });

        this.markers.push(dynaMarker);
    },*/
    
    onOpenMapDeal: function() {
		if (!this.getMapDealView()) {
			this.setMapDealView(Ext.create('SmartConsumer.view.Maps'));
		}

		// Ext.Viewport.animateActiveItem(this.getDirectoryView(), {
			// type : 'slide',
			// direction : 'left'
		// });
		Ext.Viewport.setActiveItem(this.getMapDealView());
	}
});
