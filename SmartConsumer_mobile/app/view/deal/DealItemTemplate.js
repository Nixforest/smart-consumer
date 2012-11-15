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
		      '<div class="dealVourcherSave"><div class="dealIsVoucher">Giao san pham</div><div class="dealSave">Tiết kiệm: {save}%</div></div>',
		      '<img class="dealImage" onclick="window.open(\'{link}\');" src="{imageLink}" />',
		      '<div class="dealDescription">{description}</div>',
		      '<div class="dealPrice"><div class="dealSavePrice">Giá bán: {price} VNĐ</div><div class="dealBasicPrice">Giá gốc : {basicPrice} VNĐ</div></div>',
		      '<div class="dealTimeBuyer"><div class="dealNumberBuyer">Số người đã mua: {numberBuyer}</div><div class="dealTime">{endTime}</div></div>'
		].join('')
	}
});