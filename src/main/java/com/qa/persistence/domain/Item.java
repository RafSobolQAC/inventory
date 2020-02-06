package com.qa.persistence.domain;
import java.math.BigDecimal;
public class Item {
	private String name;
	private BigDecimal price = new BigDecimal(0);
	private int id;
	
	public Item() {
		this.name = null;
		this.id = -1;
	}
	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		this.id = -1;
	}
	
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
	
}
