package com.gordo.propeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gordo.sample.Customer;

public class PropEditorRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/prop-editor-context.xml");

		Customer customer = (Customer) applicationContext.getBean("customer");

		System.out.println("CUSTOMER DETAIL: " + customer.toString());

		String[] beans = applicationContext.getBeanDefinitionNames();

		for (String b : beans) {
			System.out.println("BEAN Name = '" + b + "'");
			String[] aliases = applicationContext.getAliases(b);
			for (String a : aliases) {
				System.out.println("\t with alias='" + a + "'");
			}
		}

	}

}
