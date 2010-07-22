package com.gordo.sample;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Required;

import com.gordo.propeditor.PhoneNumber;

//@Component("customer")
public class CustomerImpl implements Customer {
	String firstName = null;
	String lastName = null;
	PhoneNumber phoneNumber = null;

	public CustomerImpl() {
		System.out.println("CUSTIMPL constructed - " + this.toString());
	}
	
	public CustomerImpl(String lastName) {
		this.lastName = lastName;
		System.out.println("CUSTIMPL lastname constructed = " + this.toString());
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
//	@Required
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
		System.out.println("**** Post Construct");
	}

	@Override
	public String toString() {
		return "CustomerImpl [firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + "]";
	}

	// @Override
	// public void slap() {
	// System.out.println(this.getClass().getName() + " has been SLAPPED");
	//
	// }

}
