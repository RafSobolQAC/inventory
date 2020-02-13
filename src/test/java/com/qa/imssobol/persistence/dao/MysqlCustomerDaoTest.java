package com.qa.imssobol.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

import com.qa.imssobol.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class MysqlCustomerDaoTest {
	
	public static final Logger LOGGER = Logger.getLogger(MysqlCustomerDaoTest.class);

	private Customer customer;
	private Customer other;
	
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
	private MysqlCustomerDao custDaoMock;

		
	@Before
	public void setUpCustomer() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(mockPs);
		when(mockConn.createStatement()).thenReturn(mockStmt);
		when(mockStmt.executeQuery(Mockito.anyString())).thenReturn(mockRs);
		when(mockPs.executeQuery()).thenReturn(mockRs);
		when(mockRs.getString("name")).thenReturn("Thomas");
		when(mockRs.getInt("id")).thenReturn(1);

		customer = new Customer(1,"Thomas");
		other = new Customer(2,"Bobby");
	}

	@Test
	public void customerDaoCreateTest() {
		customer.setName("One!");
		Mockito.doReturn(customer).when(custDaoMock).readLatest();
		assertEquals("One!",custDaoMock.create(customer).getName());
		
	}

	@Test
	public void customerDaoReadByIdTest() throws SQLException {
		when(mockRs.next()).thenReturn(true);
		assertEquals(customer,custDaoMock.readById(Mockito.anyInt()));
	}
	
	@Test
	public void customerDaoReadLatestTest() throws SQLException {
		when(mockRs.next()).thenReturn(true);
		assertEquals(customer,custDaoMock.readLatest());
	}
	
	@Test
	public void customerDaoReadAllTest() throws SQLException {
		Mockito.doReturn(true).doReturn(true).doReturn(false).when(mockRs).next();
		assertEquals(2,custDaoMock.readAll().size());
	}
	
	@Test
	public void customerDaoUpdateTest() {
		Mockito.doReturn(other).when(custDaoMock).readById(Mockito.anyInt());
		assertEquals(other,custDaoMock.update(1, other));
		
	}
	
	
	@Test
	public void customerDaoDeleteTest() {
		assertTrue(custDaoMock.delete(Mockito.anyInt()));
	}
	

}
