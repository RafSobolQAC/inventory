package com.qa.persistence.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private int id;
	private BigDecimal price;
	private int customerId;
	private HashMap<Item,Integer> items;
	
	public Order(int id, int customerId, HashMap<Item,Integer> items) {
		this.id = id;
		this.price = new BigDecimal(0); //add method of calculating price!
		this.customerId = customerId;
		this.items = items;
	}
	
	public Order() {
		this.id = 0;
		this.customerId = 0;
		this.price = new BigDecimal(0);
		this.items = new HashMap<Item,Integer>();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		BigDecimal runningTotal = new BigDecimal(0);
		for (Item item : items.keySet()) {
			runningTotal = runningTotal.add(item.getPrice().multiply(new BigDecimal(items.get(item))));
		}
		this.price = (runningTotal.compareTo(new BigDecimal(10000))> 0) ? runningTotal.multiply(BigDecimal.valueOf(0.9)) : runningTotal; 
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public HashMap<Item,Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Item,Integer> items) {
		this.items = items;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
