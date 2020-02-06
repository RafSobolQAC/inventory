package com.qa.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Item;
import com.qa.utils.Login;

public class MysqlItemDao implements Dao<Item> {


	public static final Logger LOGGER = Logger.getLogger(MysqlItemDao.class);

	private Connection connection;
	private static final String INSERT = "INSERT INTO items (name, price) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE items SET name=?, price=? WHERE id=?";
	private static final String READBYID = "SELECT * FROM items WHERE id=?";
	private static final String DELETE = "DELETE FROM items WHERE id=?";
	private static final String READALL = "SELECT * FROM items";

	public MysqlItemDao() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:mysql://34.89.63.19:3306/inventory", "root",
				Login.getPassword());
	}

	@Override
	public boolean create(Item t) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT)) {

			ps.setString(1, t.getName());
			ps.setBigDecimal(2, t.getPrice());

			ps.executeUpdate();
//			ps.close();

			System.out.println("Added item: " + t.toString());
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Item readById(int id) {
		Item item = null;
		try (PreparedStatement ps = connection.prepareStatement(READBYID)) {
//			PreparedStatement ps = connection.prepareStatement(READBYID);

			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				item = new Item();
				item.setName(resultSet.getString("name"));
				item.setId(resultSet.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public ArrayList<Item> readAll() {
		ArrayList<Item> items = new ArrayList<Item>();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(READALL)) {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(READALL);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				BigDecimal price = resultSet.getBigDecimal("price");
				items.add(new Item(id, name, price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return items;
	}

	@Override
	public boolean update(int id, Item t) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {

			ps.setString(1, t.getName());
			ps.setBigDecimal(2, t.getPrice());
			ps.setInt(3, id);

			ps.executeUpdate();

			System.out.println("Item with id " + id + " got updated: " + t.toString());
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE)) {

			ps.setInt(1, id);

			ps.executeUpdate();
//			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
