Ext.define('SmartConsumer.view.ListDeal', {
	extend: 'Ext.Container',
	
	requires: ['SmartConsumer.view.deal.DealItemTemplate'],
	config: {
    	layout: {
    		type: 'vbox'
    	},
    	items: [
    	    {
    	    	xtype: 'toolbar',
        		cls: ['banner-toolbar', 'panel-shadow'],
        		id: 'banner-toolbar',
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
    	    	xtype: 'panel',
    	    	centered: true,
    	    	//scrollable: true,
    	    	items: [
    	    	    {
    	    	    	xtype: 'dealItem'
    	    	    }
    	    	]
    	    }
    	]
    }
});