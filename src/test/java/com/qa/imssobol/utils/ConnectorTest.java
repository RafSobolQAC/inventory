package com.qa.imssobol.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

@RunWith(MockitoJUnitRunner.class)
public class ConnectorTest {

	@Mock
	private Connection mockConn;
	@Mock
	private Loginner mockLoginner;

	@Spy
	@InjectMocks
	private Connector mockConnector;

	@Before
	public void init() throws SQLException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void constructorTest() throws SQLException {
//        Mockito.doReturn("Password").when(mockLoginner).logIn();
		assertEquals(mockConn, mockConnector.getConnection());
	}

	
	/**
	 * Tests setUpConnector method.
	 * <String>any(): String or null.
	 * @throws SQLException
	 */
	@Test
	public void setUpConnectorTest() throws SQLException {
		Mockito.when(mockConnector.getSystemPwd()).thenReturn("PWD");
		Mockito.doReturn(mockConn).when(mockConnector).setUpConnection(Mockito.<String>any(), Mockito.<String>any(),
				Mockito.<String>any());

		mockConnector.setUpConnector();
		Mockito.verify(mockConnector,Mockito.times(1)).setUpConnection(Mockito.<String>any(), Mockito.<String>any(),
				Mockito.<String>any());
	}
	
	
	@Test
	public void setUpConnectorNullPwdTest() throws Exception {
		Mockito.when(mockConnector.getSystemPwd()).thenReturn(null);
		Mockito.doReturn(mockLoginner).when(mockConnector).getLoginner();
		Mockito.doReturn("PWD").when(mockLoginner).logIn();
		Mockito.doReturn(mockConn).when(mockConnector).setUpConnection(Mockito.<String>any(), Mockito.<String>any(),
				Mockito.<String>any());

		mockConnector.setUpConnector();
		Mockito.verify(mockConnector,Mockito.times(1)).setUpConnector();

	}
	@Test(expected = SQLException.class)
	public void setUpConnectorSQLExceptionTest() throws SQLException {
		Mockito.when(mockConnector.getSystemPwd()).thenReturn("PWD");
		Mockito.doThrow(SQLException.class).when(mockConnector).setUpConnection(Mockito.<String>any(), Mockito.<String>any(),
				Mockito.<String>any());

		mockConnector.setUpConnector();
	}

}
