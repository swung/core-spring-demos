package com.gordondickens.sample;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

public interface ExceptionThrower {

	public String throwDFE() throws DataFormatException;

	public String throwIRE() throws InsufficientResourcesException;

	public SimpleCustomer getCustomer();

	public NotACustomer getNonCustomer();

}
