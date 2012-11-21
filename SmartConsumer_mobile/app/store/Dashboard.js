Ext.define('SmartConsumer.store.Dashboard', {
    extend: 'SmartConsumer.base.Store',
    requires: [
        'SmartConsumer.model.Dashboard'
    ],

    config :{
        model: 'SmartConsumer.model.Dashboard',
        autoLoad: true,
        root: {
            expanded: true
        },
		data:[
		  {
			 "label": "List",
			 "urlId": "list"         
		  }, 
		  {
			 "label": "About",
			 "urlId": "about"         
		  },
		  {
			"label": "Maps",
			"urlId": "map"
		  }
	   ]
    }		
});
