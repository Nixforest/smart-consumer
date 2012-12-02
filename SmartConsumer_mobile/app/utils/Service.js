Ext.define('SmartConsumer.utils.Service', {
	alias: 'service',
	singleton: true,
	
	config: {
		urlMapDeal: 'http://e-smartconsumer.appspot.com/getListDeal.app?limit=10',
		urlListDeal: 'http://ismartconsumer.appspot.com/getListAddress.app?limit=50'
	},
	
	getUrlMapDeals: function() {
		return this.getUrlMapDeal();
	}
});
