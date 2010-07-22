package com.gordo.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:META-INF/spring/app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleServiceTests {

	@Autowired
	ApplicationContext context;

	@Autowired
	Customer customer;

	@Autowired
	ExampleService service;

	@Before
	public void setup() {
		// context = new ClassPathXmlApplicationContext(
		// "META-INF/spring/app-context.xml");
		System.out.println("----- BEANS FOUND -----");

		String[] beans = context.getBeanDefinitionNames();

		for (String bean : beans) {
			System.out.println("Bean Named: " + bean);
			// System.out.println("Bean Named: " + );
		}

		System.out.println("----- *********** -----");

	}

	@Test
	public void testReadOnce() throws Exception {
//		service = (ExampleService) context.getBean("service");
		// service = (ExampleService) context.getBean("service");
		// service = (ExampleService) context.getBean("service");
		assertEquals("Hello Gordo!", service.getMessage());
	}

	@Test
	public void getCustomerTest() throws Exception {
		// Customer customer = context.getBean("customer", Customer.class);
		System.out.println("customer: " + customer);
		assertNotNull(customer);
//		Customer c2 = context.getBean("customer", Customer.class);
//		assertNotSame(customer, c2);

		customer.setFirstName("Gordonicus");
		
	}
	
	@Test
	public void slapCustomer() {
//		customer.slap();
	}

}
