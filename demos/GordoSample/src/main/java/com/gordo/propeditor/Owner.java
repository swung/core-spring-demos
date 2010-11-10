package com.gordo.propeditor;

import org.springframework.beans.factory.annotation.Autowired;

public class Owner {
	@Autowired
	Minion minion;

	public Minion getMinion() {
		return minion;
	}

	@Override
	public String toString() {
		return "Owner [minion=" + minion + ", getMinion()=" + getMinion()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

//	public void setMinion(Minion minion) {
//		this.minion = minion;
//	}
	
	
}
