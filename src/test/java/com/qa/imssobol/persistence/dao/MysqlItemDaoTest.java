package com.qa.imssobol.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

@RunWith(MockitoJUnitRunner.class)
public class MysqlItemDaoTest {
	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDaoTest.class);

	private Item item;
	private Item other;
	private BigDecimal price = BigDecimal.valueOf(5.0);
	
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
	private MysqlItemDao itemDaoMock;

		
	@Before
	public void setUpItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(mockPs);
		when(mockConn.createStatement()).thenReturn(mockStmt);
		when(mockStmt.executeQuery(Mockito.anyString())).thenReturn(mockRs);
		when(mockPs.executeQuery()).thenReturn(mockRs);
		when(mockRs.getString("name")).thenReturn("TestItem1");
		when(mockRs.getInt("id")).thenReturn(1);

		item = new Item("TestItem1",price);
	}

	@Test
	public void itemDaoCreateTest() {
		item.setName("One!");
		Mockito.doReturn(item).when(itemDaoMock).readLatest();
		assertEquals("One!",itemDaoMock.create(item).getName());
		
	}
	@Test
	public void itemDaoCreateExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		itemDaoMock.create(item);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

	@Test
	public void itemDaoReadByIdTest() throws SQLException {
		when(mockRs.next()).thenReturn(true);
		assertEquals(item.getName(),itemDaoMock.readById(Mockito.anyInt()).getName());
	}
	@Test
	public void itemDaoReadByIdExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		itemDaoMock.readById(1);
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}

	@Test
	public void itemDaoReadLatestTest() throws SQLException {
		when(mockRs.next()).thenReturn(true);
		assertEquals(item.getName(),itemDaoMock.readLatest().getName());
	}
	@Test
	public void itemDaoReadLatestExceptionTest() throws SQLException {
		when(mockConn.createStatement()).thenThrow(SQLException.class);
		itemDaoMock.readLatest();
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}
	@Test
	public void itemDaoReadLatestNoItemTest() throws SQLException {
		when(mockRs.next()).thenReturn(false);
		itemDaoMock.readLatest();
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();

	}

	@Test
	public void itemDaoReadAllTest() throws SQLException {
		Mockito.doReturn(true).doReturn(true).doReturn(false).when(mockRs).next();
		assertEquals(2,itemDaoMock.readAll().size());
	}
	@Test
	public void itemDaoReadAllExceptionTest() throws SQLException {
		when(mockConn.createStatement()).thenThrow(SQLException.class);
		itemDaoMock.readAll();
		Mockito.verify(mockPs, Mockito.times(0)).executeQuery();
	}

	@Test
	public void itemDaoUpdateTest() {
		
		assertEquals(other,itemDaoMock.update(1, item));
		
	}
	@Test
	public void itemDaoUpdateExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		itemDaoMock.update(1,item);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

	
	@Test
	public void itemDaoDeleteTest() {
		assertTrue(itemDaoMock.delete(Mockito.anyInt()));
	}
	@Test
	public void itemDaoDeleteExceptionTest() throws SQLException {
		when(mockConn.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
		itemDaoMock.delete(1);
		Mockito.verify(mockPs, Mockito.times(0)).executeUpdate();
	}

}
