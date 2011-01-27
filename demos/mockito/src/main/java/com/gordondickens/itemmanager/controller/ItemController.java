package com.gordondickens.itemmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;

@RooWebScaffold(path = "item", formBackingObject = Item.class)
@RequestMapping("/item/**")
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	public ItemController() {

	}

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		try {
			Item item = itemService.getItem(id);
			modelMap.addAttribute("item", item);
			return "item/show";
		} catch (ItemNotFoundException e) {
			modelMap.put("exception", e);
			return "redirect:/errorView";
		}
	}

	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		itemService.deleteItem(id);
		return "redirect:/item?page="
				+ ((page == null) ? "1" : page.toString()) + "&size="
				+ ((size == null) ? "10" : size.toString());
	}

	@RequestMapping(value = "/item", method = RequestMethod.DELETE)
	public String deleteItems(@RequestParam("ids") List<Long> ids) {
		if (ids == null)
			throw new IllegalArgumentException("An Identifier List is required");
		for (Long id: ids) {
		itemService.deleteItem(id);
		}
		return "redirect:/item";
	}
}
