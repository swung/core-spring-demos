package com.gordo.itemmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gordo.itemmanager.entity.Item;
import com.gordo.itemmanager.exception.ItemNotFoundException;
import com.gordo.itemmanager.service.internal.ItemService;

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
		for (Long id : ids) {
			itemService.deleteItem(id);
		}
		return "redirect:/item";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Item item, BindingResult result,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			modelMap.addAttribute("item", item);
			return "item/create";
		}
		item.persist();
		return "redirect:/item/" + item.getId();
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		modelMap.addAttribute("item", new Item());
		return "item/create";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			ModelMap modelMap) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			modelMap.addAttribute("items", Item.findItemEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) Item.countItems() / sizeNo;
			modelMap.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			modelMap.addAttribute("items", Item.findAllItems());
		}
		return "item/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Item item, BindingResult result,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			modelMap.addAttribute("item", item);
			return "item/update";
		}
		item.merge();
		return "redirect:/item/" + item.getId();
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("item", Item.findItem(id));
		return "item/update";
	}

	Converter<Item, String> getItemConverter() {
		return new Converter<Item, String>() {
			public String convert(Item item) {
				return new StringBuilder().append(item.getName()).toString();
			}
		};
	}

	@InitBinder
	void registerConverters(WebDataBinder binder) {
		if (binder.getConversionService() instanceof GenericConversionService) {
			GenericConversionService conversionService = (GenericConversionService) binder
					.getConversionService();
			conversionService.addConverter(getItemConverter());
		}
	}
}
