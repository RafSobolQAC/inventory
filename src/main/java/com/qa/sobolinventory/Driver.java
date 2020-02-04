package com.qa.sobolinventory;

public interface Driver {
	public abstract void add(Object obj);
	public abstract void update(Object obj);
	public abstract void read(int id);
	public abstract void delete(int id);
}
