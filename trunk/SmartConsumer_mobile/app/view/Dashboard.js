Ext.define('SmartConsumer.view.Dashboard', {
    extend: 'Ext.dataview.DataView',
    alias: 'widget.dashboard',
	
	config: {
        id: 'dashboard',
        scrollable: false,
		store: 'Dashboard',
        baseCls: 'dashboards-list',

        itemTpl: [
            '<img class="icon"  src="resources/icons/{urlId}.png" />',
            '<div class="name" >{label}</div>'
        ].join('')
    },
});