Ext.define('SmartConsumer.view.Maps', {
	extend: 'Ext.Container',
	requires: [
	    'SmartConsumer.view.map.MapDeal',
	    'Ext.field.Select'
	],
	
	config: {
		layout: 'vbox',
		items: [
	    	{
    	    	xtype: 'toolbar',
        		cls: ['banner-toolbar', 'panel-shadow'],
        		docked: 'top',
        		flex: 1,
        		layout: {
        			align: 'center',
        			pack: 'right'
        		},
        		items: [
        		    {
        		    	xtype: 'selectfield',
        		    	itemId: 'selectRadius',
        		    	options: [
	                        {text: '1 km', value: '1000'},
	                        {text: '2 km', value: '2000'},
	                        {text: '3 km', value: '3000'},
	                        {text: '4 km', value: '4000'},
	                        {text: '5 km', value: '5000'},
	                        {text: '6 km', value: '6000'},
	                        {text: '10 km', value: '10000'},
	                        {text: '20 km', value: '20000'}
	                    ]
        		    },
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
    	    	xtype: 'mapdeal',
    	    	flex: 20,
    	    	itemId: 'mapDeal'
    	    }
		]
	}
});
