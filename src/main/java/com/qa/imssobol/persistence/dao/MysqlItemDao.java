package com.qa.imssobol.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.utils.Utils;

public class MysqlItemDao implements Dao<Item> {

	public static final Logger LOGGER = Logger.getLogger(MysqlItemDao.class);

	private Connection connection;
	private static final String INSERT = "INSERT INTO items (name, price) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE items SET name=?, price=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM items WHERE id=?";
	private static final String DELETE = "DELETE FROM items WHERE id=?";
	private static final String READALL = "SELECT * FROM items";

	public MysqlItemDao(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Reads the latest Item from the database (one with the highest ID).
	 * 
	 * @return the latest Item
	 */
	public Item readLatest() {
		Item item = new Item();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
			if (resultSet.next()) {
				item.setName(resultSet.getString("name"));
				item.setId(resultSet.getInt("id"));
				item.setPrice(resultSet.getBigDecimal("price"));
				return item;
			} else {
				LOGGER.warn("There is no order yet!");

			}
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);

		}
		return null;
	}

	/**
	 * Sends an Item to the database.
	 * 
	 * @param item the item to be created
	 * @return the Item created
	 */
	@Override
	public Item create(Item item) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT)) {

			ps.setString(1, item.getName());
			ps.setBigDecimal(2, item.getPrice());

			ps.executeUpdate();

			return readLatest();

		} catch (SQLException e) {

			Utils.exceptionLogger(e, LOGGER);
		}
		return null;
	}

	/**
	 * Reads an Item with the given ID from the database.
	 * 
	 * @param id item's ID (in the database)
	 * @return the Item
	 */
	@Override
	public Item readById(int id) {
		Item item = null;
		ResultSet resultSet = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {

			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				item = new Item();
				item.setName(resultSet.getString("name"));
				item.setId(resultSet.getInt("id"));
				item.setPrice(resultSet.getBigDecimal("price"));
			} else {
				LOGGER.warn("Item with this ID doesn't exist!");
				return new Item();
			}

		} catch (SQLException e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				Utils.exceptionLogger(e, LOGGER);
				item = new Item();
			}

		}
		return item;
	}

	/**
	 * Reads all items from the database, and stores them in an arraylist.
	 * 
	 * @return an arraylist of Items
	 */
	@Override
	public ArrayList<Item> readAll() {
		ArrayList<Item> items = new ArrayList<>();
		ResultSet resultSet = null;
		try (Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(READALL);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				BigDecimal price = resultSet.getBigDecimal("price");
				items.add(new Item(id, name, price));
			}
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				Utils.exceptionLogger(e, LOGGER);
				items = new ArrayList<>();
			}
		}
		return items;
	}

	/**
	 * Updates an item with the provided id, changing their details into those from
	 * the new item.
	 * 
	 * @param id   item's id in the database to be modified
	 * @param item new item's details (the ID of that item doesn't affect anything)
	 * @return the new Item
	 */
	@Override
	public Item update(int id, Item item) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			ps.setString(1, item.getName());
			ps.setBigDecimal(2, item.getPrice());
			ps.setInt(3, id);

			ps.executeUpdate();

			return readById(id);

		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
		}
		return null;

	}

	/**
	 * Deletes an item with the specified ID.
	 * 
	 * @param id the item's id
	 * @return true if no exceptions (whether deleted or not), false otherwise
	 */
	@Override
	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setInt(1, id);

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			Utils.exceptionLogger(e, LOGGER);
			return false;
		}

	}

}
