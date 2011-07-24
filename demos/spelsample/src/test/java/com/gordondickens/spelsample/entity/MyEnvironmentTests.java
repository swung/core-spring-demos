package com.gordondickens.spelsample.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MyEnvironmentTests {
	private static final Logger logger = LoggerFactory
			.getLogger(MyEnvironmentTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	ConfigurationClassPostProcessor classPostProcessor;

	// @Before
	// public void setup() {
	// EvaluationContext context = new StandardEvaluationContext();
	// context.setVariable("systemProperties", System.getProperties());
	// Properties sysProps = System.getProperties();
	// Enumeration<String> e = (Enumeration<String>) sysProps.propertyNames();
	//
	// while (e.hasMoreElements()) {
	// String key = e.nextElement();
	// logger.debug("**********************");
	// logger.debug("****** {} -- {} ******",
	// new String[] { key, sysProps.getProperty(key) });
	// logger.debug("**********************");
	// }
	// logger.debug("System Properties set to {}", System.getProperties()
	// .toString());
	// }

	@Test
	public void testSpelValues() {
		MyEnvironment myEnvironment = new MyEnvironment();
		logger.debug("******** myEnvironment {} ********",
				myEnvironment.toString());
		logger.debug(
				"\n\n**********************\nSystemEnvironment {}\n**********************\n\n",
				myEnvironment.getSystemEnvironment());
		logger.debug(
				"\n**********************\n SystemProperties {}\n**********************\n\n",
				myEnvironment.getSystemProperties());
		// assertNotNull("Var One MUST have a value",
		// myEnvironment.getVarOne());
		// assertNotNull("System Environment MUST have a value",
		// myEnvironment.getSystemEnvironment());
		// assertNotNull("System Properties MUST have a value",
		// myEnvironment.getSystemProperties());
	}

	@Before
	public void setup() {
		logger.debug("********************");
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

		logger.debug("********************");
		logger.debug("*** Number of Beans = {} ***",
				applicationContext.getBeanDefinitionCount());
		logger.debug("********************");

		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>Config Class Post Processor {}",
				classPostProcessor.toString());
	}

}
