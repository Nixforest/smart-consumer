Ext.define('SmartConsumer.view.Service', {
	extend: 'Ext.Container',
	
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
				xtype: 'panel',
				layout: 'hbox',
				items: [
				    {
				    	xtype: 'textfield',
						label: 'List Deal url',
						style: 'width: 80%'
				    },
				    {
				    	xtype: 'button',
				    	text: 'Submit',
				    	ui: 'action',
				    	name: 'listDealUrlButton'
				    }
				]
			},
			{
				xtype: 'panel',
				layout: 'hbox',
				items: [
				    {
				    	xtype: 'textfield',
						label: 'Map Deal url',
						style: 'width: 80%'
				    },
				    {
				    	xtype: 'button',
				    	text: 'Submit',
				    	ui: 'action',
				    	name: 'mapDealUrlButton'
				    }
				]
			}
		]
	}
});
