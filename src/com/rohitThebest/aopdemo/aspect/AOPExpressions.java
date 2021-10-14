package com.rohitThebest.aopdemo.aspect;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {

	/*
	 * Here the Pointcut declaration will match for addAccount() method for any class
	 * which has a method name addAccount() and if it founds a method call to the
	 * addAccount() it will invoke this method before it
	 */
	@Pointcut("execution(public void addAccount())")
	public void pointcutExpression1() {
	}

	/*
	 * Here the Pointcut declaration will match for addAccount() method for only the
	 * AccountDAO class as we have specified the fully qualified class name
	 */
	@Pointcut("execution(public void com.rohitThebest.aopdemo.dao.AccountDAO.addAccount())")
	public void pointcutExpression2() {
	}

	/*
	 * This pointcut declaration will match any method which starts with 'add' and
	 * will invoke the beforeAddAccountAdvice() before it
	 */
	@Pointcut("execution(public void add*())")
	public void pointcutExpression3() {
	}

	/*
	 * This pointcut declaration will match only the methods with return type boolean
	 */
	@Pointcut("execution(public boolean add*())")
	public void pointcutExpression4() {
	}

	/*
	 * This pointcut declaration will match the methods with any return type
	 */
	@Pointcut("execution(public * add*())")
	public void pointcutExpression5() {
	}

	/*
	 * This will match the method with -> return type : any -> starting with add ->
	 * having only 1 parameter of type com.rohitThebest.aopdemo.Account
	 */
	@Pointcut("execution(public * add*(com.rohitThebest.aopdemo.Account))")
	public void pointcutExpression6() {
	}

	/*
	 * This will match the method with -> return type : any -> starting with add ->
	 * having a parameter of type Account, followed by any number of parameters
	 */
	@Pointcut("execution(public * add*(com.rohitThebest.aopdemo.Account, ..))")
	private void pointcutExpression7() {
	}

	/*
	 * This will match the methods with -> return type : any -> starting with add ->
	 * having any number of parameters
	 */
	@Pointcut("execution(public * add*(..))")
	private void pointcutExpression8() {
	}

	/*
	 * This will match the methods 
	 * -> which are in the package :com.rohitThebest.aopdemo.dao 
	 * -> Any class (first star) -> Any Method (secondstar) 
	 * -> return type : any -> having any number of parameters
	 */
	@Pointcut("execution(* com.rohitThebest.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}
	
	// ---------------------------
	
	// Combining Pointcut declarations
		
	@Pointcut("execution(* com.rohitThebest.aopdemo.dao.*.get*(..))")
	public void getters() {}
		
	@Pointcut("execution(* com.rohitThebest.aopdemo.dao.*.set*(..))")
	public void setters() {}
		
	// combining three pointcut declaration to make one pointcut declaration
	@Pointcut("forDaoPackage() && !(getters() || setters())")
	public void forDaoPackageNotGetterSetter() {}

	// ----------------------------

}
