package com.qa.persistence.domain;

public class Item {
	private String name;
	private double price;
	private int id;
	
	public Item() {
		this.name = null;
		this.price = 0;
		this.id = -1;
	}
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = -1;
	}
	
	public Item(int id, String name, double price) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
