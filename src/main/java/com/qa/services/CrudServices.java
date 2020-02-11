package com.qa.services;

import java.util.List;

public interface CrudServices<T> {
	
    public List<T> readAll();
    
    T readById(int id);
    
    T create(T t);
     
    T update(int id, T t);
 
    boolean delete(int id);

}
