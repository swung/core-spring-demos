package com.gordondickens.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SetterTracker {
	Logger logger = LoggerFactory.getLogger(SetterTracker.class);

	@Before("execution(void set*(*))")
	public void trackChange(JoinPoint point) {
		String name = point.getSignature().getName();
		Object newValue = point.getArgs()[0];
		logger.debug(name + " about to change to '" + newValue + "' on "
				+ point.getTarget());

	}

	@Before("execution(void slap())")
	public void trackSlap(JoinPoint point) {
		logger.debug("About to SLAP!!! '" + "' on " + point.getTarget());
	}

}
