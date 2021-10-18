package com.rohitThebest.aopdemo.aspect;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rohitThebest.aopdemo.Account;

import sun.util.logging.resources.logging;

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

	@Around("execution (* com.rohitThebest.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		
		// print out which method we are advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @Around on method: " + method);

		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null;
		
		try {
			
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {

			// log the exception
			System.out.println("Exception : " + e.getMessage());
			
			// re-throw the exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display
		long duration = end - begin;
		System.out.println("\n====> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	
	
	
	@After("execution(* com.rohitThebest.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @After (finally) on method: " + method);

		
	}
	
	// Advice to be executed if a method exits by throwing an exception.
	@AfterThrowing(
		pointcut = "execution(* com.rohitThebest.aopdemo.dao.AccountDAO.findAccounts(..))",
		throwing = "theExec"
			)
	public void afterThrowingFindAccountAdvice(
			JoinPoint joinPoint, Throwable theExec
			) {
		
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @AfterThrowing on method: " + method);

		// log the exception
		System.out.println("\n======> The exeption is: " + theExec);
	}
	


	/**
	 * @AfterReturning : Advice to be executed after a join point completes 
	 * normally: for example, if a method returns without throwing an exception.
	 */
	// add new method for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut = "execution(* com.rohitThebest.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint joinPoint, List<Account> result) {
		
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		System.out.println("\n====> result is: " + result);
		
		
		// let's post-process the data and modify it
		
		// convert the account names to upper-case
		for(Account tempAccount : result) {
			
			String upperCaseName = tempAccount.getName().toUpperCase();
			
			tempAccount.setName(upperCaseName);
		}
		
		System.out.println("\n====> result is: " + result);
	}
	
	
	
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
