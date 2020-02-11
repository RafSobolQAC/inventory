package com.qa.services;

import java.util.List;

public interface CrudServices<T> {

    public List<T> readAll();
     
    void create(T t);
     
    void update(int id, T t);
 
    void delete(int id);

}
