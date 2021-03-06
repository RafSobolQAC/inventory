package com.qa.imssobol.controller;

import java.util.List;

public interface CrudController<T> {
    
    List<T> readAll();
     
    T readById();
    
    T create();
     
    T update();
     
    boolean delete();

}
