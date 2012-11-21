Ext.define('SmartConsumer.store.Deals', {
	extend: 'SmartConsumer.base.Store',
	requires: [
	     'SmartConsumer.model.Deal',
	     'Ext.data.proxy.Rest'
	],
	
	config: {
		autoLoad: true,
		model: 'SmartConsumer.model.Deal',
		
		proxy: {
			type: 'rest',
			url: 'http://e-smartconsumer.appspot.com/getListDeal.app?limit=10'
		}
	}
});
