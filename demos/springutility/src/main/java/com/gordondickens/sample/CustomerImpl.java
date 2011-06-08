package com.gordondickens.sample;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gordondickens.propeditor.PhoneNumber;

@Component("customer")
public class CustomerImpl implements Customer {
	private static final Logger logger = LoggerFactory
			.getLogger(CustomerImpl.class);

	String firstName = null;
	String lastName = null;
	PhoneNumber phoneNumber = null;

	public CustomerImpl() {
		logger.debug("CUSTIMPL constructed = '{}'", this.toString());
	}

	public CustomerImpl(String lastName) {
		this.lastName = lastName;
		logger.debug("CUSTIMPL lastname constructed = '{}'", this.toString());
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	// @Required
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public void setPhoneNumber(PhoneNumber pn) {
		this.phoneNumber = pn;
	}

	@PostConstruct
	public void runThis() {
		logger.debug("**** Post Construct");
	}

	@Override
	public String toString() {
		return "CustomerImpl [firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + "]";
	}

	// @Override
	// public void slap() {
	// logger.debug(this.getClass().getName() + " has been SLAPPED");
	//
	// }

}
