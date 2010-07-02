package com.gordo.jmxitems.controller;

import javax.validation.Valid;

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

import com.gordo.jmxitems.entity.Item;

@RequestMapping("/items")
@Controller
public class ItemController {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Item item, BindingResult result,
			ModelMap modelMap) {
		if (item == null)
			throw new IllegalArgumentException("A item is required");
		if (result.hasErrors()) {
			modelMap.addAttribute("item", item);
			return "items/create";
		}
		item.persist();
		return "redirect:/items/" + item.getId();
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		modelMap.addAttribute("item", new Item());
		return "items/create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("item", Item.findItem(id));
		return "items/show";
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
			modelMap
					.addAttribute(
							"maxPages",
							(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
									: nrOfPages));
		} else {
			modelMap.addAttribute("items", Item.findAllItems());
		}
		return "items/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Item item, BindingResult result,
			ModelMap modelMap) {
		if (item == null)
			throw new IllegalArgumentException("A item is required");
		if (result.hasErrors()) {
			modelMap.addAttribute("item", item);
			return "items/update";
		}
		item.merge();
		return "redirect:/items/" + item.getId();
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("item", Item.findItem(id));
		return "items/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		Item.findItem(id).remove();
		return "redirect:/items?page="
				+ ((page == null) ? "1" : page.toString()) + "&size="
				+ ((size == null) ? "10" : size.toString());
	}

	@InitBinder
	void registerConverters(WebDataBinder binder) {
		if (binder.getConversionService() instanceof GenericConversionService) {
			GenericConversionService conversionService = (GenericConversionService) binder
					.getConversionService();
		}
	}
}
