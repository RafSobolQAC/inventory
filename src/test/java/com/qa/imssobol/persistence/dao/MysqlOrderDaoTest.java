package com.qa.imssobol.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.persistence.domain.Item;
import com.qa.imssobol.persistence.domain.Order;

@RunWith(MockitoJUnitRunner.class)
public class MysqlOrderDaoTest {
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDaoTest.class);

	private Order order;
	private Order other;
	private Map<Item,Integer> itemQuants;
	private final BigDecimal price = BigDecimal.valueOf(5.0);
	private MysqlOrderDao orderDao;
	
	@Mock
	private Order mockOrder;
	
	@Mock
	private PreparedStatement mockPs;
	
	@Mock
	private Statement mockStmt;
	
	@Mock
	private ResultSet mockRs;
	
	@Mock
	private Connection mockConn;
	
	@Spy
	@InjectMocks
	private MysqlOrderDao orderDaoMock;

	
	
//	@BeforeClass
//	public static void login() throws SQLException {
//
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
//		} catch (SQLException e) {
//			LOGGER.warn(e.getMessage());
//		}
//	}
//
	@Before
	public void setUpOrder() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(mockPs);
		order = new Order();
		other = new Order();

//		orderDao = new MysqlOrderDao(conn);
		itemQuants = new HashMap<>();
		itemQuants.put(new Item(1,"TestName",price),5);
		itemQuants.put(new Item(2,"TestName2",price),2);
		
//		when(rs.getInt("id")).thenReturn(1);
//		when(rs.getBigDecimal("total_price")).thenReturn(BigDecimal.valueOf(100.0));
//		when(rs.getInt("customer_id_fk")).thenReturn(1);
		
	}
	
	@Test
	public void orderDaoCreateTest() {
		order.setItems(itemQuants);
		order.setCustomerId(1);
		order.setId(1);
		order.setPrice(BigDecimal.valueOf(100.0));
		Mockito.doReturn(order).when(orderDaoMock).readLatest();
		
		assertEquals(order,orderDaoMock.create(order));
	}
	
	@Test
	public void orderDaoReadAllTest() throws SQLException {
		when(mockStmt.executeQuery(Mockito.anyString())).thenReturn(mockRs);
		Mockito.doReturn(true).doReturn(false).when(mockRs).next();
		when(mockConn.createStatement()).thenReturn(mockStmt);
		when(mockRs.getInt("id")).thenReturn(1);
		when(mockRs.getBigDecimal("total_price")).thenReturn(BigDecimal.valueOf(100.0));
		when(mockRs.getInt("customer_id_fk")).thenReturn(1);
		order.setItems(itemQuants);
//		Mockito.doReturn(order).when(orderDaoMock).readById(Mockito.anyInt());
		assertEquals(1, orderDaoMock.readAll().get(0).getId());
	}
	
	
}
