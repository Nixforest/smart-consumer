Ext.define('SmartConsumer.store.Maps', {
    extend: 'Ext.data.Store',
    requires: [
        'SmartConsumer.model.Map',
        'Ext.data.proxy.Rest'
    ],
    
    config: {
        autoLoad: true,
        model: 'SmartConsumer.model.Map',
        storeId: 'mapStore',
        
        proxy: {
            type: 'rest',
            url: 'http://i-smartconsumer.appspot.com/getListAddress.app?limit=20',
            //url: 'http://e-smartconsumer.appspot.com/getListAddress.app?limit=20',
            reader: {
                type: 'json',
            }
        }
    }
});
