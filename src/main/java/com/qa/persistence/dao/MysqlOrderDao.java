package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Order;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Customer;

public class MysqlOrderDao implements Dao<Order> {
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);
	private Connection connection;
	private static final String INSERTPRICE = "INSERT INTO orders (total_price) VALUES (?)";
	private static final String INSERTORDERLINE = "INSERT INTO orders_items (order_id_fk, item_id_fk, quantity) VALUES (?,?,?)";
	private static final String UPDATEPRICE = "UPDATE orders SET total_price=? WHERE id=?";
	private static final String UPDATEORDERLINE = "UPDATE orders_items SET quantity=? WHERE order_id_fk=? AND item_id_fk=?";
	private static final String READBYID = "select orders.id as order_id, customer_id_fk as customer_id, item_id_fk as item_id, quantity from orders  inner join orders_items on orders.id = orders_items.order_id_fk where id = ?";
	private static final String DELETE = "DELETE FROM customers WHERE id=?";
	private static final String READALL = "SELECT * FROM orders";

	@Override
	public boolean create(Order t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order readById(int id) {
		Order order = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {
//			PreparedStatement ps = connection.prepareStatement(READBYID);

			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("id"));
				
				
				//needs work!!!!!
			} else {
				LOGGER.warn("Customer with ID does not exist!");
				return new Order();
			}

		} catch (SQLException e) {

			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public boolean update(int id, Order t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
