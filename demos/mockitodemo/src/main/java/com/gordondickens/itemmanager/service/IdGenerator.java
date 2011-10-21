package com.gordondickens.itemmanager.service;

public final class IdGenerator {

	/**
	 * @return A new ID based on the current time.
	 */
	public static long generateNewId() {
		return System.currentTimeMillis();
	}
}