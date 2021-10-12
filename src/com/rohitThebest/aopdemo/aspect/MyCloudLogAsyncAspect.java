package com.rohitThebest.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

	@Before("com.rohitThebest.aopdemo.aspect.AOPExpressions.forDaoPackage()")
	public void logToCloudAsync() {

		System.out.println("\n=====>>> Executing @Before advice : MyCloudLogAsyncAspect");
	}
}
