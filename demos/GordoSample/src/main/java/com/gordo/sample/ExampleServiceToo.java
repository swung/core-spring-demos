package com.gordo.sample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;


/**
 * {@link Service} with hard-coded input data.
 */
public class ExampleServiceToo implements Service, InitializingBean {
	
	public ExampleServiceToo() {
		System.out.println("EStoo CONSTRUCTED address = " + this.toString());
	}
	
	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		return "Hello Gordo!";	
	}
	
	@PostConstruct
	private void pcInit() {
		System.out.println("1. ES2 post-construct = " + this.toString());
	}

	private void imInit() {
		System.out.println("2. ES2 Init Method = " + this.toString());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("3. ES2 After Properties Set = " + this.toString());
		
	}
	
	
}
