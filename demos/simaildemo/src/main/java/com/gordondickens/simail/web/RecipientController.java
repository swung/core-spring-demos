package com.gordondickens.simail.web;

import org.springframework.roo.addon.layers.repository.jpa.RooRepositoryJpa;

import com.gordondickens.simail.entity.Recipient;

@RooRepositoryJpa(domainType = Recipient.class)
public interface RecipientController {

}
// public class RecipientController {
// @Autowired
// MailGateway mailGateway;
//
// @RequestMapping(method = RequestMethod.POST)
// public String create(@Valid Recipient recipient, BindingResult result,
// ModelMap modelMap) {
// if (result.hasErrors()) {
// modelMap.addAttribute("recipient", recipient);
// return "recipients/create";
// }
// recipient.persist();
// mailGateway.sendMail(recipient);
// return "redirect:/recipients/" + recipient.getId();
// }
// }
