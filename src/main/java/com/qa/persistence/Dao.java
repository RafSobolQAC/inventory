package com.qa.persistence;

import java.util.ArrayList;

public interface Dao<T> {
	public void create(T t);
	public ArrayList<T> readAll();
	public void update(int id, T t);
	public void delete(int id);
}

