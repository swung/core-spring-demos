package com.gordo.jmxitems.service;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service("itemService")
@ManagedResource("jmxitems:name=itemService")
public class ItemServiceImpl implements ItemService {

	@ManagedOperation(description = "CalculatePartCost")
	@ManagedOperationParameter(description = "Part Number", name = "partNumber")
	public void calculatePartCost(String partNumber) {
		System.out.println("Calculating Part Cost for Part Number '"
				+ partNumber + "'");
	}
}
