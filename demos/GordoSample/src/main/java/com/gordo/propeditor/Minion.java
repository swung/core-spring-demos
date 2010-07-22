package com.gordo.propeditor;

import org.springframework.stereotype.Component;

@Component("annotatedMinion")
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
