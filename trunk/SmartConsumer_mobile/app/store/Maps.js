Ext.define('SmartConsumer.store.Maps', {
    extend: 'Ext.data.Store',
    requires: [
        'SmartConsumer.model.Map'
    ],
    config: {
        autoLoad: true,
        model: 'SmartConsumer.model.Map',
        storeId: 'mapStore',
        proxy: {
            type: 'rest',
            url: 'http://i-smartconsumer.appspot.com/getListAddress.app?limit=10',
            reader: {
                type: 'json',
            }
        },
        
        /*data: [
            {
            	'title': 'ao dep',
            	'longitude': '10.851009',
            	'latitude': '106.628653'
            },
            {
            	'title': 'ao dep lam',
            	'longitude': '10.851009',
            	'latitude': '106.628653'
            }
        ]*/
    }
});
