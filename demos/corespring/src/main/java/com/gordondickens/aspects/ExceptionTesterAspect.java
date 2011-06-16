package com.gordondickens.aspects;

import java.util.zip.DataFormatException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExceptionTesterAspect {
	Logger logger = LoggerFactory.getLogger(ExceptionTesterAspect.class);

	@Before("execution(void set*(*))")
	public void trackChange(JoinPoint point) {
		String name = point.getSignature().getName();
		Object newValue = point.getArgs()[0];
		logger.debug(name + " about to change to '" + newValue + "' on "
				+ point.getTarget());

	}

	@AfterThrowing(throwing="e", value="execution(* com.gordondickens.sample.MyExceptionThrower.*(..) throws java.util.zip.DataFormatException)")
	public void trackSlap(JoinPoint point, DataFormatException e) {
		logger.debug("Catching DataFormatException!!! '" + "' on " + point.getTarget() + " on " + point.getSignature().getName());
	}

}
