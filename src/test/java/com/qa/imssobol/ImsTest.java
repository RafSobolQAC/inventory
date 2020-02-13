package com.qa.imssobol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.qa.imssobol.persistence.dao.MysqlCustomerDao;
import com.qa.imssobol.persistence.domain.Customer;
import com.qa.imssobol.persistence.domain.Domain;
import com.qa.imssobol.services.CustomerServices;
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
	private CustomerServices mockCustServ;
	
	@Mock
	private MysqlCustomerDao mockCustDao;
	
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
		Customer customer = new Customer("Bobby");
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
//		Ims testIms = new Ims(mockConnector);
//		Mockito.when(ims.makeConnector(Mockito.anyString())).thenReturn(new Connector("TestUrl"));
		Mockito.when(mockCrudCont.create()).thenReturn(customer);
		Mockito.when(mockCrudCont.readAll()).thenReturn(customers);
		Mockito.when(mockCrudCont.readById()).thenReturn(customer);
		Mockito.when(mockCrudCont.update()).thenReturn(customer);
		Mockito.when(mockCrudCont.delete()).thenReturn(true);

		ims.doAction(mockCrudCont, Action.CREATE);
		Mockito.verify(mockCrudCont,Mockito.times(1)).create();
		ims.doAction(mockCrudCont, Action.READALL);
		Mockito.verify(mockCrudCont,Mockito.times(1)).readAll();
		ims.doAction(mockCrudCont, Action.READ);
		Mockito.verify(mockCrudCont,Mockito.times(1)).readById();
		ims.doAction(mockCrudCont, Action.UPDATE);
		Mockito.verify(mockCrudCont,Mockito.times(1)).update();
		ims.doAction(mockCrudCont, Action.DELETE);
		Mockito.verify(mockCrudCont,Mockito.times(1)).delete();
		ims.doAction(mockCrudCont,Action.RETURN);
		Mockito.verifyNoMoreInteractions(mockCrudCont);
	}
	
	@Test
	public void imsRunnerTest() throws SQLException {
		Mockito.doReturn(mockConn).when(mockConnector).getConnection();
		Mockito.doNothing().when(ims).doAction(Mockito.any(CrudController.class), Mockito.any(Action.class));
		Mockito.doReturn(Domain.CUSTOMER).doReturn(Domain.STOP).when(ims).getDomain();
		Mockito.doReturn(Action.CREATE).when(ims).getAction();
		ims.imsRunner();
		Mockito.verify(ims,Mockito.times(1)).doAction(Mockito.any(CrudController.class), Mockito.any(Action.class));
		Mockito.doReturn(Domain.ITEM).doReturn(Domain.STOP).when(ims).getDomain();
		Mockito.doReturn(Action.UPDATE).when(ims).getAction();
		ims.imsRunner();
		Mockito.verify(ims,Mockito.times(2)).doAction(Mockito.any(CrudController.class), Mockito.any(Action.class));
		Mockito.doReturn(Domain.ORDER).doReturn(Domain.STOP).when(ims).getDomain();
		Mockito.doReturn(Action.READALL).when(ims).getAction();
		ims.imsRunner();
		Mockito.verify(ims,Mockito.times(3)).doAction(Mockito.any(CrudController.class), Mockito.any(Action.class));
		Mockito.doReturn(Domain.STOP).doReturn(Domain.STOP).when(ims).getDomain();
		ims.imsRunner();
		Mockito.verify(ims,Mockito.times(3)).doAction(Mockito.any(CrudController.class), Mockito.any(Action.class));


	}
}
