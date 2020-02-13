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
		when(mockConn.createStatement()).thenReturn(mockStmt);
		when(mockStmt.executeQuery(Mockito.anyString())).thenReturn(mockRs);
		itemQuants = new HashMap<>();
		itemQuants.put(new Item(1,"TestName",price),5);
		itemQuants.put(new Item(2,"TestName2",price),2);

		order = new Order();
		other = new Order();
		order.setItems(itemQuants);
		order.setCustomerId(1);
		order.setId(1);
		order.setPrice(BigDecimal.valueOf(100.0));

//		orderDao = new MysqlOrderDao(conn);
		
//		when(rs.getInt("id")).thenReturn(1);
//		when(rs.getBigDecimal("total_price")).thenReturn(BigDecimal.valueOf(100.0));
//		when(rs.getInt("customer_id_fk")).thenReturn(1);
		
	}
	
	@Test
	public void orderDaoCreateTest() {
		Mockito.doReturn(order).when(orderDaoMock).readLatest();

		assertEquals(order,orderDaoMock.create(order));
	}
	@Test
	public void orderDaoCreateExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		orderDaoMock.create(order);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

	@Test
	public void orderDaoReadAllTest() throws SQLException {
		Mockito.doReturn(true).doReturn(false).when(mockRs).next();
		when(mockRs.getInt("id")).thenReturn(1);
		when(mockRs.getBigDecimal("total_price")).thenReturn(BigDecimal.valueOf(100.0));
		when(mockRs.getInt("customer_id_fk")).thenReturn(1);
		order.setItems(itemQuants);
		assertEquals(1, orderDaoMock.readAll().get(0).getId());
	}
	@Test
	public void orderDaoReadAllExceptionTest() throws SQLException {
		when(mockConn.createStatement()).thenThrow(SQLException.class);
		orderDaoMock.readAll();
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}

	@Test
	public void orderDaoReadLatestTest() throws SQLException {
		Mockito.doReturn(true).when(mockRs).next();
		when(mockRs.getInt("id")).thenReturn(1);
		Mockito.doReturn(order).when(orderDaoMock).readById(Mockito.anyInt());
		assertEquals(order,orderDaoMock.readLatest());
	}
	@Test
	public void orderDaoReadLatestExceptionTest() throws SQLException {
		when(mockConn.createStatement()).thenThrow(SQLException.class);
		orderDaoMock.readLatest();
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}

	@Test
	public void orderDaoReadByIdTest() throws SQLException {
		when(mockPs.executeQuery()).thenReturn(mockRs);
		when(mockRs.getInt("id")).thenReturn(1);
		when(mockRs.getInt("customer_id_fk")).thenReturn(1);
		when(mockRs.getBigDecimal("total_price")).thenReturn(BigDecimal.valueOf(100.0));
		Mockito.doReturn(true).doReturn(true).doReturn(false).when(mockRs).next();
//		Mockito.doReturn(order).when(mockOrder).setItems(itemQuants);

		assertEquals(order.getPrice(),orderDaoMock.readById(1).getPrice());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void orderDaoReadByIdNoSuchItemTest() throws SQLException {
		when(mockPs.executeQuery()).thenReturn(mockRs);
		Mockito.doReturn(false).when(mockRs).next();
		orderDaoMock.readById(1);
	}
	
	@Test
	public void orderDaoReadByIdExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		orderDaoMock.readById(1);
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}
	
	@Test
	public void orderDaoUpdateTest() throws SQLException {
		Mockito.doReturn(order).when(orderDaoMock).readById(Mockito.anyInt());
		assertEquals(order,orderDaoMock.update(1, order));
	}
	@Test
	public void orderDaoUpdateExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		orderDaoMock.update(1,order);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

	@Test
	public void orderDaoDeleteTest() {
		assertEquals(true,orderDaoMock.delete(Mockito.anyInt()));
	}
	@Test
	public void orderDaoDeleteExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		orderDaoMock.delete(1);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

}
