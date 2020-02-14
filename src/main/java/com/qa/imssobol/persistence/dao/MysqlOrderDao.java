package com.qa.imssobol.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private static final String READORDER = "select * from orders where id=?";
	private static final String READBYID = "select `id` as order_id, `customer_id_fk` as customer_id, `item_id_fk` as item_id, `quantity`, `total_price` from orders inner join orders_items on orders.`id` = orders_items.`order_id_fk` where orders.`id` = ?";
	private static final String READORDERLINE = "select * from (select order_id_fk as order_id, item_id_fk as item_id, quantity, price as item_price, name as item_name from orders_items inner join items on orders_items.item_id_fk = items.id) orderjoin where order_id = ?";
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

	/**
	 * Reads the latest Order from the database (one with the highest ID).
	 * 
	 * @return the latest Order
	 */
	public Order readLatest() {
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				return readById(id);
			} else {
				LOGGER.warn("There is no order yet!");
			}

		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Sends an Order to the database.
	 * 
	 * @param order the order to be created
	 * @return the Order created
	 */
	@Override
	public Order create(Order order) {
		try (PreparedStatement ps = connection.prepareStatement(INSERTORDER);
				PreparedStatement ps2 = connection.prepareStatement(INSERTORDERLINE)) {

			ps.setInt(1, order.getCustomerId());
			ps.setBigDecimal(2, order.getPrice());

			ps.executeUpdate();
			int lastId = readLatest().getId();
			for (Item item : order.getItems().keySet()) {
				ps2.setInt(1, lastId);
				ps2.setInt(2, item.getId());
				ps2.setInt(3, order.getItems().get(item));
				ps2.executeUpdate();
			}

			return readLatest();

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return order;

	}

	/**
	 * Reads all orders from the database.
	 * @return an ArrayList of Orders from the database (0 price - read by int to get the price of each item!)
	 */
	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(READALL);) {
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
/**
 * Reads an order based on its ID.
 * @param id the order's ID in the database
 * @return the Order
 */
	@Override
	public Order readById(int id) {
		Order order = null;
		ResultSet resultSet = null;
		Map<Item, Integer> itemQuants = new HashMap<>();
		Item itemToAdd = new Item();
		try (PreparedStatement ps = connection.prepareStatement(READORDER);
				PreparedStatement ps2 = connection.prepareStatement(READORDERLINE)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setCustomerId(resultSet.getInt("customer_id_fk"));
				order.setPrice(resultSet.getBigDecimal("total_price"));

				ps2.setInt(1, id);
				try (ResultSet resultSet2 = ps2.executeQuery()) {
					while (resultSet2.next()) {
						itemToAdd.setId(resultSet2.getInt("item_id"));
						itemToAdd.setName(resultSet2.getString("item_name"));
						itemToAdd.setPrice(resultSet2.getBigDecimal("item_price"));
						itemQuants.put(itemToAdd, resultSet2.getInt("quantity"));
					}
					order = order.setItems(itemQuants);
				} 
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
				Utils.exceptionLogger(e, LOGGER);
			}
		}

		return order;
	}
/**
 * Updates an order with id ID, to the details of the order provided. The ID doesn't matter.
 * @param id the order's ID in the database
 * @param order the new order's details
 * @return the new order
 */
	@Override
	public Order update(int id, Order order) {
		HashMap<Item, Integer> itemsQuants;
		BigDecimal price = order.getPrice();
		int itemId;
		int itemQuantity;
		try (PreparedStatement ps = connection.prepareStatement(UPDATEPRICE);
				PreparedStatement ps2 = connection.prepareStatement(UPDATEORDERLINE)) {

			itemsQuants = (HashMap<Item, Integer>) order.getItems();
			for (Item item : itemsQuants.keySet()) {
				itemId = item.getId();
				itemQuantity = itemsQuants.get(item);
				ps2.setInt(1, itemQuantity);
				ps2.setInt(2, id);
				ps2.setInt(3, itemId);
				ps2.executeUpdate();
			}
			ps.setBigDecimal(1, price);
			ps.setInt(2, id);
			ps.executeUpdate();

			return readById(id);

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		}

		return null;
	}
/**
 * Deletes an order from the database, with the provided ID.
 * @param id the order's ID in the database
 * @return true if no exception was caught, false otherwise
 */
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
