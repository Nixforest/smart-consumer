Ext.define('SmartConsumer.controller.Service', {
	extend: 'Ext.app.Controller',
	
	config: {
		routes: {
			'service': 'onOpenService'
		},
		
		refs: {
			listDealUrlButton: 'button[name="listDealUrlButton"]',
			mapDealUrlButton: 'button[name="mapDealUrlButton"]'
		},
		
		control: {
			listDealUrlButton: {
				'tap': 'onTapListDealUrlButton'
			},
			
			mapDealUrlButton: {
				'tap': 'onTapMapDealUrlButton'
			}
		},
		
		serviceView: null
	},
	
	onOpenService: function() {
		if (!this.getServiceView()) {
			this.setServiceView(Ext.create('SmartConsumer.view.Service'));
		}

		// Ext.Viewport.animateActiveItem(this.getDirectoryView(), {
			// type : 'slide',
			// direction : 'left'
		// });
		Ext.Viewport.setActiveItem(this.getServiceView());
	},
	
	onTapListDealUrlButton: function() {
		console.log(SmartConsumer.utils.Service);
	},
	
	onTapMapDealUrlButton: function() {
		
	}
});
