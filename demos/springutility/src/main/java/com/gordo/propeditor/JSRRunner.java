package com.gordo.propeditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JSRRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/META-INF/spring/app-context.xml");
		
		Logger logger = LoggerFactory.getLogger(JSRRunner.class);

//		Customer customer = (Customer) applicationContext.getBean("customer");
//		Customer cust2 = (Customer) applicationContext.getBean("cust2");
//		Customer cust3 = (Customer) applicationContext.getBean("cust3");
//
//		logger.debug(customer.toString());
//		logger.debug(cust2.toString());
//		logger.debug(cust3.toString());
//		
//		try {
//			Customer daParent = (Customer) applicationContext
//					.getBean("daparent");
//			System.err.println("DaParent = " + daParent.toString());
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//		}

//		Owner xmlOwner = (Owner) applicationContext.getBean("xmlOwner");
//		Owner annotationOwner = (Owner) applicationContext.getBean("annotatedOwner");
//		
//		Assert.isTrue("Bartleby".equals(xmlOwner.getMinion().getName()));
//		Assert.isTrue("Cletus".equals(annotationOwner.getMinion().getName()));
//		
//		
//		logger.debug("XML - " + xmlOwner.getMinion().toString());
//		logger.debug("Annotated - " + annotationOwner.getMinion().toString());
		
		logger.debug("******************************************************************************");
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (String o : beans) {
			logger.debug("________________________");
			logger.debug("BEAN = " + o);
			logger.debug("\tType = " + applicationContext.getType(o));
			String [] aliases = applicationContext.getAliases(o);
			if (aliases != null && aliases.length > 0) {
				for (String a: aliases) {
					logger.debug("\tAliased as: " + a);
				}
//				logger.debug("\tAliased as: " + aliases.toString());
			}

		}
		
		logger.debug("******************************************************************************");

	}

}
