package com.gordo.itemmanager.controller;

import com.gordo.itemmanager.entity.Item;
import java.lang.Long;
import java.lang.String;
import javax.validation.Valid;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect ItemController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String ItemController.create(@Valid Item item, BindingResult result, ModelMap modelMap) {
        if (item == null) throw new IllegalArgumentException("A item is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return "item/create";
        }
        item.persist();
        return "redirect:/item/" + item.getId();
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ItemController.createForm(ModelMap modelMap) {
        modelMap.addAttribute("item", new Item());
        return "item/create";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ItemController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, ModelMap modelMap) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            modelMap.addAttribute("items", Item.findItemEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Item.countItems() / sizeNo;
            modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            modelMap.addAttribute("items", Item.findAllItems());
        }
        return "item/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ItemController.update(@Valid Item item, BindingResult result, ModelMap modelMap) {
        if (item == null) throw new IllegalArgumentException("A item is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return "item/update";
        }
        item.merge();
        return "redirect:/item/" + item.getId();
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ItemController.updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        modelMap.addAttribute("item", Item.findItem(id));
        return "item/update";
    }
    
    Converter<Item, String> ItemController.getItemConverter() {
        return new Converter<Item, String>() {
            public String convert(Item item) {
                return new StringBuilder().append(item.getName()).toString();
            }
        };
    }
    
    @InitBinder
    void ItemController.registerConverters(WebDataBinder binder) {
        if (binder.getConversionService() instanceof GenericConversionService) {
            GenericConversionService conversionService = (GenericConversionService) binder.getConversionService();
            conversionService.addConverter(getItemConverter());
        }
    }
    
}
