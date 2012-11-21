Ext.define('SmartConsumer.view.ListDeal', {
	extend: 'Ext.Container',
	
	requires: [
	    'SmartConsumer.view.deal.DealItemTemplate',
	    'Ext.field.Search',
	    'Ext.plugin.ListPaging'
	],
	
	config: {
    	layout: {
    		type: 'vbox'
    	},
    	items: [
    	    {
    	    	xtype: 'toolbar',
        		cls: ['banner-toolbar', 'panel-shadow'],
        		docked: 'top',
        		layout: {
        			align: 'center',
        			pack: 'right'
        		},
        		items: [
        		    {
        		    	xtype: 'button',
    					id: 'contact_home_button',
                        iconCls: 'back-button',
                        iconMask: true,
    					action: 'goBackToHome',
    					align:'left'
        		    }    
        		]
    	    },
    	    {
    	    	xtype: 'searchfield',
    	    	itemId: 'searchDeal',
    	    	placeHolder: 'Search Deal'
    	    },
    	    {
    	    	xtype: 'dealItem',
    	    	itemId: 'listDeal',
    	    	store: 'Deals',
    	    	plugins: [{
    	    		xclass: 'Ext.plugin.ListPaging',
    	    		autoPaging: true
      	    	}]
    	    }
    	]
    }
});
