package com.gordondickens.itemmanager.service;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	// *********************************************************************
	// NOTE: The code has been commented out to prove that the Mocking works
	// without an implementation.
	// *********************************************************************

	@Override
	public void deleteItem(long id) {
        logger.debug(">> deleteItem() id='{}'", id);
		// Item.findItem(id).remove();
		// TODO Auto-generated method stub
	}

	@Override
	public Item getItem(long id) throws ItemNotFoundException {
        logger.debug(">> getItem() id='{}'", id);
		// return Item.findItem(id);
		return null;
	}

}
