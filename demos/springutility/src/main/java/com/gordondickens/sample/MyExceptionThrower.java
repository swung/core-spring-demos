package com.gordondickens.sample;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

import org.springframework.stereotype.Component;

@Component
public class MyExceptionThrower {

	public String ThrowExceptionOnePlease() throws DataFormatException {
		throw new DataFormatException();
	}

	public String ThrowExceptionTwoPlease()
			throws InsufficientResourcesException {
		throw new InsufficientResourcesException();
	}
}
