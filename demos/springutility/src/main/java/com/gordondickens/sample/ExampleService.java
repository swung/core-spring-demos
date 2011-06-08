package com.gordondickens.sample;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * {@link Service} with hard-coded input data.
 */
public class ExampleService implements Service, InitializingBean {
	private static final Logger logger = LoggerFactory
			.getLogger(ExampleService.class);

	public ExampleService() {
		logger.debug("ES HAS BEEN CONSTRUCTED address = " + this.toString());
	}

	/**
	 * Reads next record from input
	 */
	@Override
	public String getMessage() {
		return "Hello Gordo!";
	}

	@PostConstruct
	private void pcInit() {
		logger.debug("1. ES @PostConstruct = " + this.toString());
	}

	private void imInit() {
		logger.debug("2. ES 'init-method' = " + this.toString());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("3. ES After Properties Set = " + this.toString());

	}

}
