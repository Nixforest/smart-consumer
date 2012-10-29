Ext.define('SmartConsumer.controller.About', {
	extend: 'Ext.app.Controller',
	
	config: {
		routes: {
			'about': 'onOpenAbout'
		},
		
		aboutView: null,
	},
	
	onOpenAbout: function() {
		if (!this.getAboutView()) {
			this.setAboutView(Ext.create('SmartConsumer.view.About'));
		}

		// Ext.Viewport.animateActiveItem(this.getDirectoryView(), {
			// type : 'slide',
			// direction : 'left'
		// });
		Ext.Viewport.setActiveItem(this.getAboutView());
	}
});