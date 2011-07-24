package com.gordondickens.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansInContextRunner {
	private static final Logger logger = LoggerFactory
			.getLogger(BeansInContextRunner.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/beans-in-context.xml");
		logger.debug("******************************************************************************");
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (String o : beans) {
			logger.debug("________________________");
			logger.debug("BEAN = " + o);
			logger.debug("\tType = " + applicationContext.getType(o));
			String[] aliases = applicationContext.getAliases(o);
			if (aliases != null && aliases.length > 0) {
				for (String a : aliases) {
					logger.debug("\tAliased as: " + a);
				}
			}
		}

		logger.debug("******************************************************************************");
		logger.debug("*** Number of Beans = {} ***",
				applicationContext.getBeanDefinitionCount());
		logger.debug("******************************************************************************");

	}

}
