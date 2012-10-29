Ext.define("SmartConsumer.view.Main", {
    extend: 'Ext.Panel',
    //mixins: [ 'Deft.mixin.Controllable' ],
    //controller: 'SmartConsumer.controller.Main',
    requires: [
        'Ext.TitleBar',
        'Ext.Video',
        'SmartConsumer.view.Dashboard'
    ],
    config: {
    	layout: {
    		type: 'vbox'
    	},
    	items: [
    	    {
    	    	xtype: 'toolbar',
        		cls: ['banner-toolbar', 'panel-shadow'],
        		id: 'banner-toolbar',
        		docked: 'top',
        		layout: {
        			align: 'center',
        			pack: 'right'
        		}
    	    },
    	    {
    	    	xtype: 'dashboard',
    	    }
    	]
    }
});
