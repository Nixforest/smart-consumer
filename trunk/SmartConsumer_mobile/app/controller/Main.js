Ext.define('SmartConsumer.controller.Main', {
	extend: 'Ext.app.Controller',
	
	config: {
		refs: {
			dashboard: 'dataview[id = "dashboard"]',
			homeButton: 'button[action = goBackToHome]'
		},
		
		control:{
			dashboard: {
				'itemtap': 'onDashboardItemTap'
			},
			homeButton: {
				'tap': 'redirectToHome'
			}
		}
	},
	
	init: function() {
		
	},
	
	onDashboardItemTap: function(view, index, target, record, e, eOpts) {
		this.redirectTo(record);
	},
	redirectToHome: function(){
		// Ext.Viewport.animateActiveItem(Ext.Viewport.getAt(0), {type: 'slide', direction: 'right'});
		Ext.Viewport.setActiveItem(0);
	},
});