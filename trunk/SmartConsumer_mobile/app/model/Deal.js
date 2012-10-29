Ext.define('SmartConsumer.model.Deal', {
	extend: 'SmartConsumer.base.Model',
	
	config: {
		fields: [
		     {
		    	 name: 'id',
		    	 type: ''
		     },
		     {
		    	 name: 'title',
		    	 type: 'string'
		     },
		     {
		    	 name: 'description',
		    	 type: 'string'
		     },
		     {
		    	 name: 'link',
		    	 type: 'string'
		     },
		     {
		    	 name: 'imageLink',
		    	 type: 'string'
		     },
		     {
		    	 name: 'price',
		    	 type: ''
		     },
		     {
		    	 name: 'unitPrice',
		    	 type: ''
		     },
		     {
		    	 name: 'basicPrice',
		    	 type: ''
		     },
		     {
		    	 name: 'save',
		    	 type: 'string'
		     },
		     {
		    	 name: 'numberBuyer',
		    	 type: 'int'
		     },
		     {
		    	 name: 'endTime',
		    	 type: 'date'
		     },
		     {
		    	 name: 'isVoucher',
		    	 type: 'boolean'
		     }
		]
	}
});