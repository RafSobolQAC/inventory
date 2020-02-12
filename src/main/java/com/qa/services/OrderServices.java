package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Order;

public class OrderServices implements CrudServices<Order>{
	Dao<Order> orderDao;
	
	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}

	public Order update(int id, Order t) {
		return orderDao.update(id, t);
	}

	public boolean delete(int id) {
		return orderDao.delete(id);
	}

	@Override
	public Order readById(int id) {
		return orderDao.readById(id);
	}


}
