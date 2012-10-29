Ext.define('SmartConsumer.view.deal.DealItemTemplate', {
	extend: 'Ext.dataview.DataView',
	alias: 'widget.dealItem',
	requires: ['SmartConsumer.store.Deals'],
	
	config: {
		id: 'dealItem',
		scrollable: true,
		store: 'Deals',
		baseCls: 'listDeal',
		
		itemTpl: [
		      '<div class="dealTitle">{title}</div>',
		      '<div class="dealVourcherSave"><div class="dealIsVoucher">Giao san pham</div><div class="dealSave">{save}</div></div>',
		      '<img class="dealImage" src="{imageLink}" />',
		      '<div class="dealDescription">{description}</div>',
		      '<div class="dealPrice"><div class="dealSavePrice">{price}</div><div class="dealBasicPrice">{basicPrice}</div></div>',
		      '<div class="dealTimeBuyer"><div class="dealNumberBuyer">{numberBuyer}</div><div class="dealTime">{endTime}</div></div>'
		].join('')
	}
});