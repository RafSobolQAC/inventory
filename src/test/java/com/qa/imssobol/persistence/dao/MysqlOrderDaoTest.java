package com.qa.imssobol.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.qa.imssobol.persistence.domain.Order;

public class MysqlOrderDaoTest {
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDaoTest.class);

	private Order order;
	private Order other;
	private static Connection conn;
	
	@Spy
	@InjectMocks
	private MysqlOrderDao orderDao;

	
	@BeforeClass
	public static void login() throws SQLException {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	@Before
	public void setUpOrder() {
		order = new Order();
		other = new Order();
	}
	
	@Test
	public void orderDaoCreateTest() {
		
	}
}
