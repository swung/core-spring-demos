package com.gordondickens.sample;

import java.util.zip.DataFormatException;

import javax.naming.InsufficientResourcesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectRunner {
	private static final Logger logger = LoggerFactory
			.getLogger(AspectRunner.class);

	static ExceptionThrower exceptionThrower;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug("********************************\n     Starting Aspect Runner\n********************************");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/AspectRunner-context.xml");
		exceptionThrower = applicationContext.getBean(ExceptionThrower.class);
		try {
			runDFE();
			runIRE();
			logger.debug("------------------------------");
			runCustomer();
			runNotACustomer();
		} catch (Exception e) {
			logger.error("Exception caught: {}", e.getMessage(), e);
		}
	}

	public static void runDFE() throws Exception {
		logger.debug("***** RUN Throwing DFE *****");
		try {
			exceptionThrower.throwDFE();
		} catch (DataFormatException dae) {
			logger.debug("Caught DFE");
		} catch (Exception e) {
			logger.error("Expected DataFormatException");
		}

	}

	public static void runIRE() throws Exception {
		logger.debug("***** RUN Throwing IRE *****");
		try {
			exceptionThrower.throwIRE();
		} catch (InsufficientResourcesException ire) {
			logger.debug("Caught IRE");
		} catch (Exception e) {
			logger.error("Expected InsufficientResourcesException");
		}
	}

	public static void runCustomer() {
		logger.debug("***** Run Customer *****");
		SimpleCustomer c = exceptionThrower.getCustomer();
		logger.debug("***** End Customer {} *****", c.toString());
	}

	public static void runNotACustomer() {
		logger.debug("***** Run NON-Customer *****");
		NotACustomer c = exceptionThrower.getNonCustomer();
		logger.debug("***** End NON-Customer {} *****", c.toString());

	}
}
