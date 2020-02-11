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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
