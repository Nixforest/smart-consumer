Ext.define('SmartConsumer.store.Deals', {
	extend: 'SmartConsumer.base.Store',
	requires: ['SmartConsumer.model.Deal'],
	
	config: {
		autoLoad: true,
		model: 'SmartConsumer.model.Deal',
		
		proxy: {
			type: 'rest',
			//url: 'http://query.yahooapis.com/v1/public/yql',
			url: 'http://e-smartconsumer.appspot.com/getListDeal.app?limit=10'
			//rootProperty: ''
		},
		/*extraParams: {
			format: 'json',
			q: 'e-smartconsumer.appspot.com/getListDeal.app?limit=10'
		},*/
		/*data: [
		    {
		    	'id': '1',
		    	'title': 'title1',
		    	'description': 'description1',
		    	'link': 'http://muachung.vn/khoa-hoc-di-choi/phieu-tap-belly-dance-16102.html',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price1',
		    	'basicPrice': 'basicPrice1',
		    	'unitPrice': 'unitPrice1',
		    	'save': 'save1',
		    	'numberBuyer': 'numberBuyer1',
		    	'endTime': 'endTime1',
		    	'isVoucher': 'isVoucher1'
		    },
		    {
		    	'id': '2',
		    	'title': 'title2',
		    	'description': 'description2',
		    	'link': 'http://muachung.vn/khoa-hoc-di-choi/phieu-tap-belly-dance-16102.html',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price2',
		    	'basicPrice': 'basicPrice2',
		    	'unitPrice': 'unitPrice2',
		    	'save': 'save2',
		    	'numberBuyer': 'numberBuyer2',
		    	'endTime': 'endTime2',
		    	'isVoucher': 'isVoucher2'
		    },
		    {
		    	'id': '3',
		    	'title': 'title3',
		    	'description': 'description3',
		    	'link': 'http://muachung.vn/khoa-hoc-di-choi/phieu-tap-belly-dance-16102.html',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price3',
		    	'basicPrice': 'basicPrice3',
		    	'unitPrice': 'unitPrice3',
		    	'save': 'save3',
		    	'numberBuyer': 'numberBuyer3',
		    	'endTime': 'endTime3',
		    	'isVoucher': 'isVoucher3'
		    },
		    {
		    	'id': '4',
		    	'title': 'title4',
		    	'description': 'description4',
		    	'link': 'link4',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price4',
		    	'basicPrice': 'basicPrice4',
		    	'unitPrice': 'unitPrice4',
		    	'save': 'save4',
		    	'numberBuyer': 'numberBuyer4',
		    	'endTime': 'endTime4',
		    	'isVoucher': 'isVoucher4'
		    },
		    {
		    	'id': '5',
		    	'title': 'title5',
		    	'description': 'description5',
		    	'link': 'link5',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price5',
		    	'basicPrice': 'basicPrice5',
		    	'unitPrice': 'unitPrice5',
		    	'save': 'save5',
		    	'numberBuyer': 'numberBuyer5',
		    	'endTime': 'endTime5',
		    	'isVoucher': 'isVoucher5'
		    },
		    {
		    	'id': '6',
		    	'title': 'title6',
		    	'description': 'description6',
		    	'link': 'link6',
		    	'imageLink': 'http://images.hotdeals.vn/images/detailed/72/Thumbnail135104427750874cb52860a.jpg',
		    	'price': 'price6',
		    	'basicPrice': 'basicPrice6',
		    	'unitPrice': 'unitPrice6',
		    	'save': 'save6',
		    	'numberBuyer': 'numberBuyer6',
		    	'endTime': 'endTime6',
		    	'isVoucher': 'isVoucher6'
		    },
	    ]*/
	}
});