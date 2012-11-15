Ext.define('SmartConsumer.model.Map', {
    extend: 'Ext.data.Model',
    config: {
        fields: [
            {
            	name: 'id',
            },
            {
                name: 'title'
            },
            {
                name: 'longitude',
                type: 'float'
            },
            {
                name: 'latitude',
                type: 'float'
            },
            {
            	name: 'fullAddress',
            	type: 'string'
            },
            {
                name: 'price'
            },
            {
            	name: 'basicPrice'
            },
            {
            	name: 'remainTime'
            },
            {
            	name: 'link',
            	type: 'string'
            },
            {
            	name: 'linkImage',
            	type: 'string'
            }
        ]
    }
});
