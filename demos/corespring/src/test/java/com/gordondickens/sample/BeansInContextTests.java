package com.gordondickens.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/UtilsTest-context.xml")
//@ContextConfiguration

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/utils-context.xml")
//@ContextConfiguration("utils-context.xml")

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/META-INF/spring/utils-context.xml")
//@ContextConfiguration(value = "META-INF/spring/utils-context.xml")

// Equivalent to:
// @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
/**
 * @author gordon
 * 
 */
@ContextConfiguration(value = "/META-INF/spring/beans-in-context.xml")
// Equivalent to:
// @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
// @ContextConfiguration(locations = { "utils-context.xml", "beans-in-context"
// })
@RunWith(SpringJUnit4ClassRunner.class)
public class BeansInContextTests {
	private static final Logger logger = LoggerFactory
			.getLogger(BeansInContextTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Before
	public void setup() {
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

	@Test
	public void showUtilsInfo() {
		// run me
	}
}
