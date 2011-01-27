package com.gordondickens.sample;

import javax.annotation.PostConstruct;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import org.springframework.beans.factory.InitializingBean;


/**
 * {@link Service} with hard-coded input data.
 */
public class ExampleService implements Service, InitializingBean {
	
	
	
	public ExampleService() {
		System.out.println("ES HAS BEEN CONSTRUCTED address = " + this.toString());
	}
	
	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		return "Hello Gordo!";	
	}
	
	@PostConstruct
	private void pcInit() {
		System.out.println("1. ES post-construct = " + this.toString());
	}

	private void imInit() {
		System.out.println("2. ES Init Method = " + this.toString());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("3. ES After Properties Set = " + this.toString());
		
	}
	
	
}
