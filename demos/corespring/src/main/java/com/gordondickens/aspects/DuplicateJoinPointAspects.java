package com.gordondickens.aspects;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DuplicateJoinPointAspects {
	// Logger logger = LoggerFactory.getLogger(DuplicateJoinPointAspects.class);
	//
	// @Before("execution(void set*(*))")
	// public void trackChange(JoinPoint point) {
	// String name = point.getSignature().getName();
	// Object newValue = point.getArgs()[0];
	// logger.debug("DUPLICATE - " + name + " about to change to '" + newValue +
	// "' on "
	// + point.getTarget());
	//
	// }
	//
	// @Before("execution(void slap())")
	// public void trackSlap(JoinPoint point) {
	// logger.debug("DUPLICATE - About to SLAP!!! '" + "' on " +
	// point.getTarget());
	// }

}
