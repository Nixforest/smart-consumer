Ext.define('SmartConsumer.controller.ListDeal', {
	extend: 'Ext.app.Controller',
	
	config: {
		routes: {
			'list': 'onOpenListDeal'
		},
		
		refs: {
			searchDeal: 'searchfield[itemId="searchDeal"]',
			listDeal: '#listDeal'
		},
		
		control: {
			searchDeal: {
				'keyup': 'onSearchDeal'
			}
		},
		
		listDealView: null
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
	},
	
	onSearchDeal: function(view, e, eOpts) {
		var store = this.getListDeal().getStore();
		store.clearFilter();
		
		if (view.getValue() != "") {
			var temp = view.getValue().toLowerCase();
			store.filterBy(function(record) {
				if (record.getData().title.toLowerCase().indexOf(temp) > -1)
					return record;
				else if (record.getData().description.toLowerCase().indexOf(temp) > -1)
					return record;
			});
		}
	}
});
