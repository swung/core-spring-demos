package com.gordo.itemmanager.exception;

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 4585978906531493046L;

	private long id;

	public ItemNotFoundException(long id) {
		this.id = id;
	}

}
