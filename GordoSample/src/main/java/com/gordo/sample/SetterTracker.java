package com.gordo.sample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SetterTracker {

	@Before("execution(void set*(*))")
    public void trackChange(JoinPoint point) {
        String name = point.getSignature().getName();
        Object newValue = point.getArgs()[0];
        System.out.println(name + " about to change to '" + newValue + 
            "' on " + point.getTarget());  
   
    }
	
	@Before("execution(void slap())")
    public void trackSlap(JoinPoint point) {
       System.out.println("About to SLAP!!! '" + 
            "' on " + point.getTarget());  
	}	
	
}
