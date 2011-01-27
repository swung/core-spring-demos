package com.gordondickens.sample;

import org.springframework.beans.factory.FactoryBean;

public class MyCustFactory implements FactoryBean<Customer> {
	
	Customer customer = null;

	@Override
	public Customer getObject() throws Exception {
		if (!isSingleton() || customer == null) {
			customer = new CustomerImpl();
		}
		return customer;
	}

	@Override
	public Class<? extends Customer> getObjectType() {
		return Customer.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
