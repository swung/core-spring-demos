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

//Equivalent to: @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
//@ContextConfiguration(value = "/META-INF/spring/utils-context.xml")
//Equivalent to: @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
@ContextConfiguration(locations = {"utils-context.xml", "app-context"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilsTest {
	private Logger logger = LoggerFactory.getLogger(UtilsTest.class);
	
	@Autowired
	ApplicationContext context;

	@Before
	public void setup() {
		logger.debug("******************************************************************************");
		String[] beans = context.getBeanDefinitionNames();
		for (String o : beans) {
			logger.debug("________________________");
			logger.debug("BEAN = " + o);
			logger.debug("\tType = " + context.getType(o));
			String[] aliases = context.getAliases(o);
			if (aliases != null && aliases.length > 0) {
				for (String a : aliases) {
					logger.debug("\tAliased as: " + a);
				}
				// logger.debug("\tAliased as: " + aliases.toString());
			}
		}

		logger.debug("******************************************************************************");
	}

	
	
	@Test
	public void showUtilsInfo() {
		
	}
}
