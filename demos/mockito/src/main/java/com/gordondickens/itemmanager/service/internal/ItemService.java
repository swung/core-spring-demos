package com.gordondickens.itemmanager.service.internal;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;

public interface ItemService {
	// Going to mock this so we do NOT have to create any implementation classes
	Item getItem(long id) throws ItemNotFoundException;

	void deleteItem(long id);
}
