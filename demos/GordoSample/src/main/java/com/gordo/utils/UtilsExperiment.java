package com.gordo.utils;

import java.util.List;

public class UtilsExperiment {
	List names;
	List otherNames;

	public List getNames() {
		return names;
	}

	public void setNames(List names) {
		this.names = names;
	}

	public List getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(List otherNames) {
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {
		return "UtilsExperiment [names (" + names.getClass().getName() + ")=" + names + ", otherNames(" + otherNames.getClass().getName() + ")=" + otherNames
				+ "]";
	}
	
	
}
