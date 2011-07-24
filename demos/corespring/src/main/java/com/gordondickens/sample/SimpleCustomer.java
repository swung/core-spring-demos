package com.gordondickens.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gordondickens.propeditor.PhoneNumber;

public class SimpleCustomer {
	private static final Logger logger = LoggerFactory
			.getLogger(SimpleCustomer.class);

	String firstName = null;
	String lastName = null;
	PhoneNumber phoneNumber = null;

	public SimpleCustomer() {
		logger.debug("CUSTIMPL constructed = '{}'", this.toString());
	}

	public SimpleCustomer(String lastName) {
		this.lastName = lastName;
		logger.debug("CUSTIMPL lastname constructed = '{}'", this.toString());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "SimpleCustomer [firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}
