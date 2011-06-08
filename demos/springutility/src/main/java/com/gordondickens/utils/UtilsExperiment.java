package com.gordondickens.utils;

import java.util.List;

public class UtilsExperiment {
	List<String> names;
	List<String> otherNames;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names.clear();
		this.names.addAll(names);
	}

	public List<String> getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(List<String> otherNames) {
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {
		return "UtilsExperiment [names (" + names.getClass().getName() + ")=" + names + ", otherNames(" + otherNames.getClass().getName() + ")=" + otherNames
				+ "]";
	}
	
	
}
