package com.qa.imssobol.persistence.domain;
import java.math.BigDecimal;
public class Item {
	private String name;
	private BigDecimal price;
	private int id;
	/**
	 * Create an empty Item instance.
	 */
	public Item() {
		this.name = null;
		this.id = -1;
	}
	
	/**
	 * Create an Item with a name and BigDecimal price, but no ID.
	 * @param name
	 * @param price
	 */
	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		this.id = -1;
	}
	
	/**
	 * Create an Item with an ID, name, and BigDecimal price.
	 * @param id
	 * @param name
	 * @param price
	 */
	public Item(int id, String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.id + " " + this.name + " " + this.price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
