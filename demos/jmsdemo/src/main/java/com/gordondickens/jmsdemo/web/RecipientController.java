package com.gordondickens.jmsdemo.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gordondickens.jmsdemo.entity.Recipient;

@RooWebScaffold(path = "recipients", formBackingObject = Recipient.class)
@RequestMapping("/recipients")
@Controller
public class RecipientController {
	@Autowired
	JmsTemplate jmsTemplate;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Recipient recipient, BindingResult result,
			ModelMap modelMap) {
		if (result.hasErrors()) {
			modelMap.addAttribute("recipient", recipient);
			return "recipients/create";
		}
		recipient.persist();
		// jmsTemplate.convertAndSend("queue.new.recipient", recipient);
		jmsTemplate.convertAndSend("queue.new.recipient", recipient.toString());
		log.debug("Sent Message '" + recipient.toString() + "' to Queue.");
		return "redirect:/recipients/" + recipient.getId();
	}

	// @RequestMapping(value="/showjms", method = RequestMethod.GET)
	// public String showjms(ModelMap modelMap) {
	// String msg = (String)
	// jmsTemplate.receiveAndConvert("queue.new.recipient");
	// modelMap.addAttribute("messagecontent", msg);
	// return "recipients/showjms";
	// }

	@RequestMapping(params = "showjms", method = RequestMethod.GET)
	public String showJms(ModelMap modelMap) {
		jmsTemplate.setReceiveTimeout(JmsTemplate.RECEIVE_TIMEOUT_NO_WAIT);
		// jmsTemplate.setReceiveTimeout(5000);
		String msg = (String) jmsTemplate
				.receiveAndConvert("queue.new.recipient");
		log.debug("*******  Received Message '" + msg + "' from Queue.");
		if (!StringUtils.hasText(msg)) {
			msg = "NO MESSAGE IN QUEUE!";
		}
		// List<String> msgs = jmsTemplate.browse (
		// new BrowserCallback<List<String>>() {
		// public List<String> doInJms(Session session, QueueBrowser
		// queueBrowser) throws JMSException {
		// Enumeration<Message> e = queueBrowser.getEnumeration();
		// List<String> list = new ArrayList<String>();
		// while (e.hasMoreElements()) {
		// Message message = e.nextElement();
		// String m = message.toString();
		// list.add(m);
		// log.debug("***** BROWSED MSG: '" + m + "'");
		//
		// }
		// log.debug("***** Returning List: " + list);
		// return list;
		// }
		// }
		// );

		modelMap.addAttribute("messagecontent", msg);
		return "recipients/showjms";
	}
}
