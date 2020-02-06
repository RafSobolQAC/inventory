package com.qa.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qa.persistence.domain.Item;

public class MysqlItemDao implements Dao<Item> {
	private Connection connection;

	public MysqlItemDao() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Password: ");
		String password = scanner.nextLine();

		this.connection = DriverManager.getConnection("jdbc:mysql://34.89.63.19:3306/inventory", "root", password);
		scanner.close();
	}

	@Override
	public void create(Item t) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Item> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, Item t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
