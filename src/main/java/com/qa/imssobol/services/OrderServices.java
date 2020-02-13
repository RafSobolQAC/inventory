package com.qa.imssobol.services;

import java.sql.Connection;
import java.util.List;

import com.qa.imssobol.persistence.dao.Dao;
import com.qa.imssobol.persistence.dao.MysqlOrderDao;
import com.qa.imssobol.persistence.domain.Order;

public class OrderServices implements CrudServices<Order>{
	Dao<Order> orderDao;
	private Connection connection;
	public OrderServices(MysqlOrderDao orderDao) {
		this.orderDao = orderDao;
		this.connection = orderDao.getConnection();
	}
	public Connection getConnection() {
		return connection;
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
