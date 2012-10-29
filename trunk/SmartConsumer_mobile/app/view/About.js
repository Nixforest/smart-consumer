Ext.define('SmartConsumer.view.About', {
	extend: 'Ext.Panel', 
	
	config: {
		title: 'About',
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
		    	styleHtmlContent: true,
		    	centered: true,
				html: ['Ho Chi Minh City National University <br />',
		    	   'University of Information Technology <br />',
		    	   'Software Faculty <br />',
		    	   'Smart Consumer Project <br />',
		    	   'Members:  <br />',
		    	   '08520181 - Cù Duy Khoa  <br />',
		    	   '08520259 - Phạm Trung Nguyên <br />'
		        ].join('')
		    }        
	    ]
	}
});