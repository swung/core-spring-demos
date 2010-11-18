package com.gordo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gordon
 * 
 */
public class UtilsRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(UtilsRunner.class);
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/spring/utils-context.xml");

		UtilsExperiment ue = (UtilsExperiment) ctx.getBean("utilsexperiment");
		logger.debug(ue.toString());

	}

}
