package com.qa.persistence.domain;

public class Customer {
	private String name;
	private int id;

	
	
	public Customer() {
		this.name = null;
		this.id = -1;
	}
	public Customer(int id, String name) {
		this.name = name;
		this.id = id;
		
	}
	public Customer(String name) {
		this.name = name;
		this.id = -1;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.id + " " + this.name;
	}
	
}
