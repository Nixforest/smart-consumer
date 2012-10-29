Ext.define('SmartConsumer.controller.ListDeal', {
	extend: 'Ext.app.Controller',
	
	config: {
		routes: {
			'list': 'onOpenListDeal'
		},
		
		listDealView: null,
	},
	
	onOpenListDeal: function() {
		if (!this.getListDealView()) {
			this.setListDealView(Ext.create('SmartConsumer.view.ListDeal'));
		}

		// Ext.Viewport.animateActiveItem(this.getDirectoryView(), {
			// type : 'slide',
			// direction : 'left'
		// });
		Ext.Viewport.setActiveItem(this.getListDealView());
	}
});