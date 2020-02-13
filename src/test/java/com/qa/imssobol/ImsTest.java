package com.qa.imssobol;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.imssobol.controller.Action;
import com.qa.imssobol.controller.CrudController;
import com.qa.imssobol.controller.CustomerController;
import com.qa.imssobol.utils.Connector;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {
	@Mock
	private Connection mockConn;

	@Mock
	private CrudController mockCrudCont;
	
	@Mock
	private CustomerController mockCustCont;
	
	@Mock
	private Connector mockConnector;
	
	
	@Spy
	@InjectMocks
	private Ims ims;
	
	
//	public void imsRunnerCustomerCreateTest() {
//		Mockito.when(Domain.getDomain()).thenReturn(Domain.CUSTOMER);
//		Mockito.when(Action.getAction()).thenReturn(Action.CREATE);
////		Mockito.when
//	}
	
	@Before
	public void init() {
		
	}
	@Test
	public void doActionTest() throws SQLException {
		Ims testIms = new Ims(mockConnector);
//		Mockito.when(ims.makeConnector(Mockito.anyString())).thenReturn(new Connector("TestUrl"));
		Mockito.when(mockCrudCont.create()).thenReturn(true);
		ims.doAction(mockCrudCont, Action.CREATE);
		Mockito.verify(mockCrudCont,Mockito.times(1)).create();
	}
}
