package com.gordondickens.itemmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;

/**
 * Item Controller Mock Test NOTE: Do NOT annotate with Context Configuration -
 * we are mocking boundaries
 * 
 * @author Gordon Dickens
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	Logger logger = LoggerFactory.getLogger(ItemControllerTest.class);
	private ItemController itemController;
	private ModelMap modelMap;

	/**
	 * Mock the Service layer, the unit under test is the Controller
	 */
	@Mock
	private ItemService itemService;

	/**
	 * Before each test inject the Mocked Service into the Controller under
	 * test. Create the model for MVC data.
	 */
	@Before
	public void setUp() {
		itemController = new ItemController(itemService);
		modelMap = new ModelMap();
	}

	/**
	 * Test when user selects an item to view, given the item number the user is
	 * returned an Item in the model and redirected to the show page.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testViewItem() throws Exception {
		logger.debug("test View Item");

		// 1. Setup a simple Item bean for the test
		Item item = new Item();
		item.setId(1l);
		item.setName("Item 1");

		// 2. When the service method getItem is called, return the created item
		when(itemService.getItem(item.getId())).thenReturn(item);

		// 3. Execute the controller show method given an Item Id
		String view = itemController.show(item.getId(), modelMap);

		// 4. Verify BEHAVIOR - getItem was invoked on the mocked service
		verify(itemService).getItem(item.getId());

		// 5. Assert end STATE for model & view
		assertEquals(item, modelMap.get("item"));
		assertEquals("item/show", view);
	}

	/**
	 * Test exception condition. Should the user attempt to delete an item that
	 * does not exist Expect the service to throw an ItemNotFoundException.
	 * 
	 * Assert that the user is redirected to the Error Page Assert that the
	 * model is returned to with the Exception
	 * 
	 * @throws Exception
	 * @See {@link ItemNotFoundException}
	 */
	@Test
	public void testViewItemWithItemNotFoundException() throws Exception {
		logger.debug("test View with Item Not Found Exception");

		Long itemId = 5L;

		// 1. Setup the exception object for the mock
		ItemNotFoundException exception = new ItemNotFoundException(itemId);

		// 2. Setup expected behavior, when getItem is called throw an exception
		when(itemService.getItem(itemId)).thenThrow(exception);

		// 3. Execute the controller method
		String view = itemController.show(itemId, modelMap);

		// 4. Verify BEHAVIOR - getItem was invoked on the mocked service
		verify(itemService).getItem(itemId);

		// 5. Assert end STATE for model & view
		assertEquals("redirect:/errorView", view);
		assertSame(exception, modelMap.get("exception"));
	}

	/**
	 * Test invocation of deleting an item
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteItem() throws Exception {
		logger.debug("test Delete Item");

		// 1. Execute the controller method
		String view = itemController.delete(5l, null, null);

		// 2. Verify that the deleteItem method is invoked on the Service class
		verify(itemService).deleteItem(5l);

		// 3. Assert view is a redirect starting with the specified string
		assertTrue(view.startsWith("redirect:/item?page="));
	}

	/**
	 * Testing behavior of the Mock. Ensure the Service's deleteItem method is
	 * invoked exactly the same number times as Item ids in list.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultipleDeletesOccur() throws Exception {
		logger.debug("test that Multiple Deletes Item");

		// 1. Setup a list of Longs to receive into the deleteItems method
		List<Long> testIds = new ArrayList<Long>();
		testIds.add(1L);
		testIds.add(300L);
		testIds.add(77L);

		// 2. Execute the Controller deleteItems method
		String view = itemController.deleteItems(testIds);

		// 3. Verify that the Service's deleteItem got executed the same number
		// of times as number of elements in the list with Any Long value.
		verify(itemService, times(testIds.size())).deleteItem(anyLong());

		// TODO - Add test to verify that the value passed in the array is
		// actually the one used?

		// 4. Assert that the user gets redirected to the Item list page
		assertTrue(view.equals("redirect:/item"));
	}

}
