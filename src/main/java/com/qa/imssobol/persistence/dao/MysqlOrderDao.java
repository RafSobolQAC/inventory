package com.qa.imssobol.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.persistence.domain.Order;
import com.qa.imssobol.utils.Utils;

public class MysqlOrderDao implements Dao<Order> {
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);
	private Connection connection;
	private static final String INSERTORDER = "INSERT INTO orders (customer_id_fk,total_price) VALUES (?,?)";
	private static final String INSERTORDERLINE = "INSERT INTO orders_items (order_id_fk, item_id_fk, quantity) VALUES (?,?,?)";
	private static final String UPDATEPRICE = "UPDATE orders SET total_price=? WHERE id=?";
	private static final String UPDATEORDERLINE = "UPDATE orders_items SET quantity=? WHERE order_id_fk=? AND item_id_fk=?";
	private static final String READBYID = "select `id` as order_id, `customer_id_fk` as customer_id, `item_id_fk` as item_id, `quantity` from orders inner join orders_items on orders.`id` = orders_items.`order_id_fk` where orders.`id` = ?";
	private static final String DELETE = "DELETE FROM orders WHERE id=?";
	private static final String DELETEORDERLINE = "DELETE FROM orders_items WHERE order_id_fk=?";
	private static final String READALL = "SELECT * FROM orders";
	private static final String GETPRICE = "SELECT item_id_fk as item_id, quantity FROM orders_items WHERE order_id_fk = ?";
	private static final String GETLASTID = "SELECT(SELECT id FROM orders ORDER BY id DESC LIMIT 1) as id";
	
	public MysqlOrderDao(Connection connection) {
		this.connection = connection;
	}
	public Connection getConnection() {
		return this.connection;
	}
	public Order readLatest() {
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				return readById(id);
			} else {
				LOGGER.warn("There is no order yet!");
			}

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order t) {
		ResultSet resultSet = null;
		try (PreparedStatement ps = connection.prepareStatement(INSERTORDER);
				PreparedStatement ps2 = connection.prepareStatement(INSERTORDERLINE);
				PreparedStatement getLastps = connection.prepareStatement(GETLASTID)) {

			ps.setInt(1, t.getCustomerId());
			ps.setBigDecimal(2, t.getPrice());

			ps.executeUpdate();
			int lastId = readLatest().getId();
			for (Item item : t.getItems().keySet()) {
				ps2.setInt(1, lastId);
				ps2.setInt(2, item.getId());
				ps2.setInt(3, t.getItems().get(item));
				ps2.executeUpdate();
			}

			return readLatest();

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
		}

		return t;

	}

	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(READALL);) {
			while (resultSet.next()) {
				Order order = new Order();
				int id = resultSet.getInt("id");
				BigDecimal price = resultSet.getBigDecimal("total_price");
				int custId = resultSet.getInt("customer_id_fk");
				order.setId(id);
				order.setCustomerId(custId);
				order.setPrice(price);
				orders.add(order);
			}
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return orders;

	}

	@Override
	public Order readById(int id) {
		Order order = null;
		ResultSet resultSet = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setCustomerId(resultSet.getInt("customer_id_fk"));
				order.setPrice(resultSet.getBigDecimal("total_price"));
				// TODO: read items from orderline
				
			} else {
				LOGGER.warn("Order with ID does not exist!");
				throw new IllegalArgumentException();
			}

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
		}

		return order;
	}

	@Override
	public Order update(int id, Order t) {
		HashMap<Item,Integer> itemsQuants;
		BigDecimal price = t.getPrice();
		int itemId;
		int itemQuantity;
		try(PreparedStatement ps = connection.prepareStatement(UPDATEPRICE);
				PreparedStatement ps2 = connection.prepareStatement(UPDATEORDERLINE)) {
			
			itemsQuants = (HashMap<Item,Integer>) t.getItems();
			for (Item item : itemsQuants.keySet()) {
				itemId = item.getId();
				itemQuantity = itemsQuants.get(item);
				ps2.setInt(1, itemQuantity);
				ps2.setInt(2, id);
				ps2.setInt(3, itemId);
				ps2.executeUpdate();
			}
			ps.setBigDecimal(1,price);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			return readById(id);
			
		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		} 		
		
		return null;
	}

	@Override
	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE);
				PreparedStatement ps2 = connection.prepareStatement(DELETEORDERLINE)) {
			ps.setInt(1, id);
			ps2.setInt(1, id);
			ps.executeUpdate();
			ps2.executeUpdate();


		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
			return false;
		}
		return true;
	}

}
