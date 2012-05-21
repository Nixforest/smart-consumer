package com.gae.java.smartconsumer.util;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<DealItem> cart;
	
	public Cart()
	{
		cart = new ArrayList<DealItem>();
	}
	
	public void addItem(DealItem item)
	{
		if(cart.contains(item))
		{
			DealItem temp = cart.get(cart.indexOf(item));
			temp.setQuantity(temp.getQuantity() + item.getQuantity());
		}
		else
			cart.add(item);
	}
	
	public void Delete(int index)
	{
		cart.remove(index);
	}
	
	public List<DealItem> getCart()
	{
		return cart;
	}
	
	public double sumMoney(){
		double money = 0;
		for(DealItem d : cart){
		money += d.getPrice() * d.getQuantity();
		}
		return money;
	}
	
	public void DeleteAll()
	{
		cart.clear();
	}
		
}
