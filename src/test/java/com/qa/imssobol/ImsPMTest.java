//package com.qa.imssobol;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.powermock.api.easymock.annotation.Mock;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.qa.imssobol.controller.Action;
//import com.qa.imssobol.controller.CrudController;
//import com.qa.imssobol.controller.CustomerController;
//import com.qa.imssobol.persistence.domain.Domain;
//import com.qa.imssobol.utils.Connector;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({Ims.class,Domain.class,Action.class})
//public class ImsPMTest {
//	
//	@Mock
//	private Connector mockConnector;
//	
//	@Mock
//	private CustomerController mockCustCont;
//	
//	@Mock
//	private CrudController mockCrudCont;
//	
//	@Spy
//	@InjectMocks
//	private Ims ims;
//
//	@Before
//	public void setUp() {
//		ims = new Ims(mockConnector);
//		
//		
//	}
//
////	@Test
////	public void imsRunnerTest() throws Exception {
////		PowerMockito.mockStatic(Domain.class);
////		PowerMockito.mockStatic(Action.class);
////		Mockito.when(Domain.getDomain()).thenReturn(Domain.CUSTOMER);
////		Mockito.when(Action.getAction()).thenReturn(Action.CREATE);
//////		PowerMockito.doReturn(Domain.CUSTOMER).when(Domain.class,"getDomain");
//////		PowerMockito.doReturn(Action.CREATE).when(Action.class,"getAction");
////		PowerMockito.whenNew(CustomerController.class).withAnyArguments().thenReturn(mockCustCont);
////		
////		PowerMockito.verifyStatic()
////		Mockito.when(ims.doAction(mockCrudCont,Action.CREATE));
////	}
//	
//	
//}
