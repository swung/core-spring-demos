package com.gordo.utils;

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
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/spring/utils-context.xml");

		UtilsExperiment ue = (UtilsExperiment) ctx.getBean("utilsexperiment");
		System.out.println(ue.toString());

	}

}
