package com.gordondickens.spelsample.web;

import com.gordondickens.spelsample.entity.MyEnvironment;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "myenvironments", formBackingObject = MyEnvironment.class)
@RequestMapping("/myenvironments")
@Controller
public class MyEnvironmentController {
}
