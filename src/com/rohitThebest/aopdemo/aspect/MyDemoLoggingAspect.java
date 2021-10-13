package com.rohitThebest.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rohitThebest.aopdemo.Account;

/*
 * @Aspect:  This is a module which has a set of APIs providing 
 * cross-cutting requirements. For example, 
 * a logging module would be called AOP aspect for logging. 
 * An application can have any number of aspects depending on the requirement.
 * 
 * 
 * @Order : By default if have more than one aspect, then the order of execution
 * of each aspect is undefined, they execute in any order,
 * therefore with @Order we can define the order of execution of each aspect 
 */
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging

	/*
	 * @Before: It is an advice type which ensures that an advice runs before the
	 * method execution.
	 */
	@Before("com.rohitThebest.aopdemo.aspect.AOPExpressions.forDaoPackageNotGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {

		System.out.println("\n=====>>> Executing @Before advice : MyDemoLoggingAspect");

		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		System.out.println("Method: " + methodSignature);

		// display method arguments
		Object[] args = joinPoint.getArgs();

		for (Object tempArg : args) {

			System.out.println(tempArg);

			if (tempArg instanceof Account) {

				// downcast and print Account specific stuff
				Account account = (Account) tempArg;

				System.out.println("account name : " + account.getName());
				System.out.println("account level : " + account.getLevel());
			}
		}
	}

	/*
	 * Here we have three advices i.e. beforeAddAccountAdvice,
	 * performApiAnalyticsAdvice, logToCloudAsync
	 * 
	 * When we run our application, the order of the execution of these advices is
	 * undefined and is executed in any order. To fix it we have to refactor our
	 * code and place each advice in different aspects and will annotate the aspects
	 * with @Order() and give an appropriate number in which we have to run the
	 * advices
	 */

//	// reusing the pointcut declaration
//	@Before("com.rohitThebest.aopdemo.aspect.AOPExpressions.forDaoPackage()")
//	public void performApiAnalyticsAdvice() {
//
//		System.out.println("\n=====>>> Executing @Before advice : performApiAnalytics");
//	}
//
//	@Before("com.rohitThebest.aopdemo.aspect.AOPExpressions.forDaoPackage()")
//	public void logToCloudAsync() {
//
//		System.out.println("\n=====>>> Executing @Before advice : logToCloudAsync");
//	}

}
