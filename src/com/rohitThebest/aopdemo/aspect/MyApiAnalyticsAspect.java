package com.rohitThebest.aopdemo.aspect;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

	@Before("com.rohitThebest.aopdemo.aspect.AOPExpressions.forDaoPackage()")
	public void performApiAnalyticsAdvice() {

		System.out.println("\n=====>>> Executing @Before advice : MyApiAnalyticsAspect");
	}
}
