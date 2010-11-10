package com.gordo.propeditor;

import javax.inject.Named;

@Named
//("annotatedMinion")
public class Minion {
	String Name = "Cletus";

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Minion [Name=" + Name + "]";
	}

}
