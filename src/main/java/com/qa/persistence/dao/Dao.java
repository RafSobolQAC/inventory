package com.qa.persistence.dao;

import java.util.ArrayList;

public interface Dao<T> {
	public boolean create(T t);
	public ArrayList<T> readAll();
	public T readById(int id);
	public boolean update(int id, T t);
	public boolean delete(int id);
}

