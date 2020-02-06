package com.qa.persistence.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
	
	private int id;
	private BigDecimal price;
	private int customerId;
	private ArrayList<Item> items;
	
	public Order(int id, int customerId, ArrayList<Item> items) {
		this.id = id;
		this.price = new BigDecimal(0); //add method of calculating price!
		this.customerId = customerId;
		this.items = items;
	}
	
	public Order() {
		this.id = 0;
		this.customerId = 0;
		this.price = new BigDecimal(0);
		this.items = new ArrayList<Item>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
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

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
