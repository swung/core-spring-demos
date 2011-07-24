package com.gordondickens.sample;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyExceptionThrower implements ExceptionThrower,
		ApplicationContextAware {
	private static final Logger logger = LoggerFactory
			.getLogger(MyExceptionThrower.class);

	ApplicationContext applicationContext;

	@Override
	public String throwDFE() throws DataFormatException {
		logger.debug("BIZ - Heads up gonna throw DFE");
		throw new DataFormatException();
	}

	@Override
	public String throwIRE() throws InsufficientResourcesException {
		logger.debug("BIZ - Heads up gonna throw IRE");
		throw new InsufficientResourcesException();
	}

	@Override
	public SimpleCustomer getCustomer() {
		logger.debug("BIZ - Returning Customer");
		SimpleCustomer c = applicationContext.getBean(SimpleCustomer.class);
		c.setFirstName("Supra");
		c.setLastName("Cztar - Customer");
		return c;
	}

	@Override
	public NotACustomer getNonCustomer() {
		logger.debug("BIZ - Returning NON-Customer");
		NotACustomer c = applicationContext.getBean(NotACustomer.class);
		c.setFirstName("Lou");
		c.setLastName("Czer - NOT CUST");
		return c;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
