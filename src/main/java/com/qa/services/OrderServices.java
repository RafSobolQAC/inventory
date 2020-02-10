package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Order;

public class OrderServices {
	Dao<Order> orderDao;
	
	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public void create(Order order) {
		orderDao.create(order);
	}

	public void update(int id, Order t) {
		
	}

	public void delete(Order t) {
		
	}


}
