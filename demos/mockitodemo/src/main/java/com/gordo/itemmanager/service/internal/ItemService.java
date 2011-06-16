package com.gordo.itemmanager.service.internal;

import com.gordo.itemmanager.entity.Item;
import com.gordo.itemmanager.exception.ItemNotFoundException;

public interface ItemService {
	// Going to mock this so we do NOT have to create any implementation classes
	Item getItem(long id) throws ItemNotFoundException;

	void deleteItem(long id);
}
