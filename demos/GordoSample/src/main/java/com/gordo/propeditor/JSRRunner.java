package com.gordo.propeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JSRRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/app-context.xml");

		Owner owner = applicationContext.getBean(Owner.class);
		System.out.println(owner.toString());
		// Customer customer = (Customer)
		// applicationContext.getBean("customer");
		// Customer cust2 = (Customer) applicationContext.getBean("cust2");
		// Customer cust3 = (Customer) applicationContext.getBean("cust3");
		//
		// System.out.println(customer.toString());
		// System.out.println(cust2.toString());
		// System.out.println(cust3.toString());
		//
		// try {
		// Customer daParent = (Customer) applicationContext
		// .getBean("daparent");
		// System.err.println("DaParent = " + daParent.toString());
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		// Owner xmlOwner = (Owner) applicationContext.getBean("xmlOwner");
		// Owner annotationOwner = (Owner)
		// applicationContext.getBean("annotatedOwner");
		//
		// Assert.isTrue("Bartleby".equals(xmlOwner.getMinion().getName()));
		// Assert.isTrue("Cletus".equals(annotationOwner.getMinion().getName()));
		//
		//
		// System.out.println("XML - " + xmlOwner.getMinion().toString());
		// System.out.println("Annotated - " +
		// annotationOwner.getMinion().toString());

		System.out
				.println("******************************************************************************");
		System.out.println("App Context Type: "
				+ applicationContext.getClass().getName());
		System.out
				.println("******************************************************************************");
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (String o : beans) {
			System.out.println("________________________");
			System.out.println("BEAN = " + o);
			System.out.println("\tType = " + applicationContext.getType(o));
			String[] aliases = applicationContext.getAliases(o);
			if (aliases != null && aliases.length > 0) {
				for (String a : aliases) {
					System.out.println("\tAliased as: " + a);
				}
				// System.out.println("\tAliased as: " + aliases.toString());
			}
			

		}

		System.out
				.println("******************************************************************************");

	}

}
