package com.gordondickens.itemmanager.service;

import org.springframework.stereotype.Service;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	// *********************************************************************
	// NOTE: The code has been commented out to prove that the Mocking works
	// without an implementation.
	// *********************************************************************

	@Override
	public void deleteItem(long id) {
		// Item.findItem(id).remove();
		// TODO Auto-generated method stub
	}

	@Override
	public Item getItem(long id) throws ItemNotFoundException {
		// return Item.findItem(id);
		return null;
	}

}
