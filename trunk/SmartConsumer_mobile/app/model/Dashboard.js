Ext.define('SmartConsumer.model.Dashboard', {
    extend: 'SmartConsumer.base.Model',   

    config: {
        fields: [
            'urlId',
            'label'
        ]       
    },

    toUrl: function() {
        return this.get('urlId');
    }
});

