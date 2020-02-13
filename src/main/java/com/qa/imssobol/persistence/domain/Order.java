package com.qa.imssobol.persistence.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private int id;
	private BigDecimal price;
	private int customerId;
	private Map<Item,Integer> items;
	/**
	 * Create an Order with an ID, customer ID, and a map of (Item, quantity). The price is calculated automatically (if the items in the map have no prices, it will be 0).
	 * @param id
	 * @param customerId
	 * @param items
	 */
	public Order(int id, int customerId, Map<Item,Integer> items) {
		this.id = id;
		this.customerId = customerId;
		this.items = items;
		this.price = calcPrice(); 
	}
	/**
	 * Create an empty Order.
	 */
	public Order() {
		this.id = 0;
		this.customerId = 0;
		this.price = new BigDecimal(0);
		this.items = new HashMap<>();
	}
	
	
	/**
	 * Create an Order with a customer ID and a map of (Item, quantity). The price is calculated automatically (if the items in the map have no prices, it will be 0).
	 * @param customerId
	 * @param items
	 */
	public Order(int customerId, Map<Item,Integer> items) {
		this.customerId = customerId;
		this.items = items;
		this.price = calcPrice();
		this.id = -1;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Calculates price of the order based on the Items inside it.
	 * @return the total price of the order
	 */
	public BigDecimal calcPrice() {
		BigDecimal runningTotal = new BigDecimal(0);
		for (Item item : items.keySet()) {
			
			runningTotal = runningTotal.add(item.getPrice().multiply(new BigDecimal(items.get(item))));
		}
		runningTotal = (runningTotal.compareTo(new BigDecimal(10000))> 0) ? runningTotal.multiply(BigDecimal.valueOf(0.9)) : runningTotal; 
		return runningTotal;
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

	public Map<Item,Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item,Integer> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerId != other.customerId)
			return false;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
