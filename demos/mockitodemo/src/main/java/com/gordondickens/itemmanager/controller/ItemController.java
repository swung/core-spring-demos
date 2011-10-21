package com.gordondickens.itemmanager.controller;

import com.gordondickens.itemmanager.entity.Item;
import com.gordondickens.itemmanager.exception.ItemNotFoundException;
import com.gordondickens.itemmanager.service.internal.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/item/**")
@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    public ItemController() {

    }

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("Path Variable 'id' is Null!");
            throw new IllegalArgumentException("An Identifier is required");
        }
        logger.debug(">> show() - id='{}'", id);
        try {
            Item item = itemService.getItem(id);
            modelMap.addAttribute("item", item);
            logger.debug("<< show() modelMap='{}'");
            return "item/show";
        } catch (ItemNotFoundException e) {
            String view = "redirect:/errorView";
            logger.debug("<< show() to view '{}' with model '{}'", view, modelMap.toString());
            modelMap.put("exception", e);
            return view;
        }
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id,
                         @RequestParam(value = "page", required = false) Integer page,
                         @RequestParam(value = "size", required = false) Integer size) {
        if (id == null) {
            logger.error("Path Variable 'id' is Null!");
            throw new IllegalArgumentException("An Identifier is required");
        }
        logger.debug(">> delete() - id='{}', page='{}', size='{}'", new String[]{id.toString(), page!=null?page.toString():"NULL", size!=null?size.toString():"NULL"});
        itemService.deleteItem(id);
        String view = "redirect:/item?page="
                + ((page == null) ? "1" : page.toString()) + "&size="
                + ((size == null) ? "10" : size.toString());
        logger.debug("<< delete() to view '{}'", view);
        return view;
    }

    @RequestMapping(value = "/item", method = RequestMethod.DELETE)
    public String deleteItems(@RequestParam("ids") List<Long> ids) {
        if (ids == null) {
            logger.error("Request parameter 'ids' is Null!");
            throw new IllegalArgumentException("An Identifier List is required");
        }
        logger.debug(">> deleteItems() - ids='{}'", ids);
        for (Long id : ids) {
            logger.debug("deleting id='{}'", id);
            itemService.deleteItem(id);
        }
        String view = "redirect:/item";
        logger.debug("<< deleteItems() to view '{}'", view);
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Item item, BindingResult result,
                         ModelMap modelMap) {
        logger.debug(">> create() - item='{}'", item);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return "item/create";
        }
        item.persist();
        String view = "redirect:/item/" + item.getId();
        logger.debug("<< create() to view '{}' with model '{}'", view, modelMap.toString());
        return view;
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        logger.debug(">> createForm()");
        modelMap.addAttribute("item", new Item());
        String view = "item/create";
        logger.debug("<< createForm() to view '{}' with model '{}'", view, modelMap.toString());
        return view;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            ModelMap modelMap) {
        logger.debug(">> list() - page='{}', size='{}'", page.toString(), size.toString());
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
        String view = "item/list";
        logger.debug("<< list() to view '{}' with model '{}'", view, modelMap.toString());
        return view;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Item item, BindingResult result,
                         ModelMap modelMap) {
        logger.debug(">> update() - item='{}'", item);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return "item/update";
        }
        item.merge();

        String view = "redirect:/item/" + item.getId();
        logger.debug("<< update() to view '{}' with model '{}'", view, modelMap.toString());
        return view;
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        logger.debug(">> updateForm() - id='{}'", id);
        modelMap.addAttribute("item", Item.findItem(id));
        String view = "item/update";
        logger.debug("<< updateForm() to view '{}' with model '{}'", view, modelMap.toString());
        return view;
    }

    Converter<Item, String> getItemConverter() {
        return new Converter<Item, String>() {
            @Override
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
