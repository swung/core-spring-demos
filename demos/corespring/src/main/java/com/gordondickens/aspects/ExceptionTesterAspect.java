package com.gordondickens.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gordondickens.sample.Customer;
import com.gordondickens.sample.NotACustomer;

@Aspect
public class ExceptionTesterAspect {
	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionTesterAspect.class);

	/**
	 * Define PointCutExpression to Advise methods, one of which throws
	 * DataFormatException
	 *
	 * @param joinPoint
	 *            - Target Method details
	 * @param e
	 *            - exception
	 */
	@AfterThrowing(throwing = "e", value = "execution(* *..MyExceptionThrower.*(..))")
	public void logException(JoinPoint joinPoint,
			java.util.zip.DataFormatException e) {
		logger.debug("ASP - (DFE NOT in PointCut) on method {} ", joinPoint
				.getSignature().getName());
	}

	@AfterThrowing(throwing = "e", value = "execution(* *..MyExceptionThrower.*(..) throws java.util.zip.DataFormatException)")
	public void logExceptionToo(JoinPoint joinPoint,
			java.util.zip.DataFormatException e) {
		logger.debug("ASP - (DFE in PointCut) on method {} ", joinPoint
				.getSignature().getName());
	}

	@AfterThrowing(throwing = "e", value = "execution(* *..MyExceptionThrower.*(..) throws java.util.zip.DataFormatException)")
	public void logExceptionThree(JoinPoint joinPoint,
			javax.naming.InsufficientResourcesException e) {
		logger.debug("ASP - (DFE in PointCut -- IRE in Advice) on method {} ",
				joinPoint.getSignature().getName());
	}

	@AfterReturning(returning = "cust", value = "execution(*..SimpleCustomer *..MyExceptionThrower.*(..))")
	public void logGetCustomer(JoinPoint joinPoint, Customer cust) {
		logger.debug(
				"ASP - (Customer in PointCut & Advice) {} with method {} ",
				cust.toString(), joinPoint.getSignature());
	}

	@AfterReturning(returning = "cust", value = "execution(*..SimpleCustomer *..MyExceptionThrower.*(..))")
	public void logGetNonCustomer(JoinPoint joinPoint, NotACustomer cust) {
		logger.debug(
				"ASP - (Customer in JoinPoint -- NON-Customer in Advice) {} with method {} ",
				cust.toString(), joinPoint.getSignature());
	}
}
