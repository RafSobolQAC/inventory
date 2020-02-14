package com.qa.imssobol.persistence.dao;

import java.util.List;

public interface Dao<T> {
	public T create(T t) ;
	public List<T> readAll();
	public T readById(int id);
	public T update(int id, T t);
	public boolean delete(int id);
}

