package com.gordondickens.itemmanager.controller;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

//NOTE: Do NOT annotate with Context Configuration - we are mocking seams (layer boundaries)
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	private ItemController itemController;

	// @Mock(answer = Answers.RETURNS_DEEP_STUBS)
	@Mock
	private ItemService itemService;
	private ModelMap modelMap;

	@Before
	public void setUp() {
		itemController = new ItemController(itemService);
		modelMap = new ModelMap();
	}

	@Test
	public void testViewItem() throws Exception {
		Item item = new Item();
		item.setId(123L);
		item.setName("Item 123");
		// when() Creates a dynamic stub
		// If real method invocation is desired, use spy
		when(itemService.getItem(item.getId())).thenReturn(item);

		String view = itemController.show(item.getId(), modelMap);

		assertEquals(item, modelMap.get("item"));
		assertEquals("item/show", view);
        verify(itemService).getItem(123L);
		// TODO: Add behavior test
		// Note: verifying stubs (@Mock & when()) is redundant, to verify actual
		// behavior,
		// use spy instead, (@Spy)
	}

	@Test
	public void testViewItemWithItemNotFoundException() throws Exception {
		ItemNotFoundException exception = new ItemNotFoundException(999);
		when(itemService.getItem(999L)).thenThrow(exception);

		String view = itemController.show(999L, modelMap);

		assertEquals("redirect:/errorView", view);
		assertSame(exception, modelMap.get("exception"));
        verify(itemService).getItem(999L);
	}

	@Test
	public void testDeleteItem() throws Exception {
		String view = itemController.delete(555L, null, null);

		verify(itemService).deleteItem(555L);
		assertTrue(view.startsWith("redirect:/item?page="));
	}

	/**
	 * Testing behavior of the Mock. Ensure that the number of items request is
	 * actually invoked.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultipleDeletesOccur() throws Exception {
		List<Long> testIds = new ArrayList<Long>();
		testIds.add(1234L);
		testIds.add(3006L);
		testIds.add(7707L);
		String view = itemController.deleteItems(testIds);

		verify(itemService, times(testIds.size())).deleteItem(anyLong());
		assertTrue(view.equals("redirect:/item")); // redir to view list page
	}

}
