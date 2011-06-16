package com.gordondickens.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gordondickens.utils.UtilsExperiment;

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/UtilsTest-context.xml")
//@ContextConfiguration

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/utils-context.xml")
//@ContextConfiguration("utils-context.xml")

//Equivalent to: @ContextConfiguration("classpath:/com/gordondickens/sample/META-INF/spring/utils-context.xml")
//@ContextConfiguration(value = "META-INF/spring/utils-context.xml")

//Equivalent to: @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
@ContextConfiguration(value = "/META-INF/spring/utils-context.xml")
// Equivalent to:
// @ContextConfiguration("classpath:/META-INF/spring/utils-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilsTest {
	private final Logger logger = LoggerFactory.getLogger(UtilsTest.class);

	@Autowired
	ApplicationContext context;

	@Test
	public void showUtilsInfo() {
		UtilsExperiment utilsExperiment = context
				.getBean(UtilsExperiment.class);
		assertNotNull(utilsExperiment);
		logger.debug(
				"\n\n************\n\n UTILS Results:\n\t{} \n\n************\n\n",
				utilsExperiment.toString());

	}
}
