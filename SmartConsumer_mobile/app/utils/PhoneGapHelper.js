Ext.define('SmartConsumer.utils.PhoneGapHelper', {
	alias: 'PhoneGapHelper',
	singleton: true,
	
	config: {},
	
	isOnline: function() {
		return navigator.onLine;
	},
	
	addEventListener: function(event, callback) {
		document.addEventListener(event, callback, false);
	}
});
