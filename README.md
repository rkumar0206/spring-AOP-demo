# spring-AOP-demo
- One of the key components of Spring Framework is the Aspect oriented programming (AOP) framework. Aspect-Oriented Programming entails breaking down program logic into distinct parts called so-called concerns. The functions that span multiple points of an application are called cross-cutting concerns and these cross-cutting concerns are conceptually separate from the application's business logic. There are various common good examples of aspects like logging, auditing, declarative transactions, security, caching, etc.
- For more go to https://www.tutorialspoint.com/spring/aop_with_spring.htm

### Aspect : 
- This is a module which has a set of APIs providing cross-cutting requirements. For example, a logging module would be called AOP aspect for logging. An application can have any number of aspects depending on the requirement.
- We mark a class as Aspect by using __@Aspect__ annotation

### Advice :
- This is the actual action to be taken either before or after the method execution. This is an actual piece of code that is invoked during the program execution by Spring AOP framework.
- There are two types of advice
  1. @Before : It is an advice type which ensures that an advice runs before the method execution.
  2. @After : It is an advice type which ensures that an advice runs after the method execution.
  
  ---
  
 ### Creating a AOP demo and using @Before() advice
 
 #### STEP 1 : Add spring jars and aspectj jar
 
 #### STEP 2 : Creating a config class
 * Here we will use complete java configuration

 ##### DemoConfig.java
    package com.rohitThebest.aopdemo;
    
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.EnableAspectJAutoProxy;
    
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan("com.rohitThebest.aopdemo")
    public class DemoConfig {
	}

#### STEP 3 : Creting AccountDAO.java class
- This class will be used for database operation, but here we will just add a method with a print statement

##### AccountDAO.java

      package com.rohitThebest.aopdemo.dao;
      
      import org.springframework.stereotype.Component;
      
      
      @Component
      public class AccountDAO {
      	
      	public void addAccount() {
      		
      		System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT");
      	}
      }

#### STEP 4 : Creting an aspect file
* This file is the actual aspect file 

##### MyDemoLoggingAspect.java

    package com.rohitThebest.aopdemo.aspect;
    
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.springframework.stereotype.Component;
    
    @Aspect
    @Component
    public class MyDemoLoggingAspect {
    
    	// this is where we add all of our related advices for logging
    	
    	@Before("execution(public void addAccount())")
    	public void beforeAddAccountAdvice() {
    		
    		System.out.println("\n=====>>> Executing @Before advice on addAccount()");
    	}
    	
    }

#### STEP 5 : Create a main app for the results

##### MainDemoApp.java

    package com.rohitThebest.aopdemo;
    
    import org.springframework.context.annotation.AnnotationConfigApplicationContext;
    
    import com.rohitThebest.aopdemo.dao.AccountDAO;
    
    public class MainDemoApp {
    
    	public static void main(String[] args) {
    
    		// read spring config java class
    		AnnotationConfigApplicationContext context =
    				new AnnotationConfigApplicationContext(DemoConfig.class);
    		
    		// get the bean from spring container
    		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
    		
    		// call the business method
    		accountDao.addAccount();
    
    		System.out.println("\nCalling the buiseness method once again");
    		
    		accountDao.addAccount();
    		
    		// close the context
    		context.close();
    	}
    
    }

- Run and observe the output : ![image](https://user-images.githubusercontent.com/63965898/136920770-4450dcb6-66b5-4850-9086-5b8c6193e8ac.png)
- We can see that before calling the accountDao.addAccount() method, the method with @Before() is called.
- Therefore **Aspect** acts as a mediator between the DAO and main class.

---

### Let's learn more about the Aspects

#### Pointcut Expressions
- It is an expression which determine the classes and the methods where the advice will be executes.
- Different exapmles of pointcut expression is given in below file :

##### AOPExpressions.java

	package com.rohitThebest.aopdemo.aspect;
	
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
		 * This will match the method with 
		 * -> return type : any 
		 * -> starting with add 
		 * -> having only 1 parameter of type com.rohitThebest.aopdemo.Account
		 */
		@Pointcut("execution(public * add*(com.rohitThebest.aopdemo.Account))")
		public void pointcutExpression6() {
		}
	
		/*
		 * This will match the method with 
		 * -> return type : any 
		 * -> starting with add 
		 * -> having a parameter of type Account, followed by any number of parameters
		 */
		@Pointcut("execution(public * add*(com.rohitThebest.aopdemo.Account, ..))")
		private void pointcutExpression7() {
		}
	
		/*
		 * This will match the methods with 
		 * -> return type : any 
		 * -> starting with add 
		 * -> having any number of parameters
		 */
		@Pointcut("execution(public * add*(..))")
		private void pointcutExpression8() {
		}
	
		/*
		 * This will match the methods 
		 * -> which are in the package :com.rohitThebest.aopdemo.dao 
		 * -> Any class (first star) 
		 * -> Any Method (secondstar) 
		 * -> return type : any 
		 * -> having any number of parameters
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
- This file contains some of the examples of pintcut expression, for more go to https://jstobigdata.com/spring/pointcut-expressions-in-spring-aop/


#### Using pointcut expressions in Aspects

##### MyDemoLoggingAspect.java

	package com.rohitThebest.aopdemo.aspect;
	
	import org.aspectj.lang.annotation.Aspect;
	import org.aspectj.lang.annotation.Before;
	import org.springframework.core.annotation.Order;
	import org.springframework.stereotype.Component;
	
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
		public void beforeAddAccountAdvice() {
	
			System.out.println("\n=====>>> Executing @Before advice : MyDemoLoggingAspect");
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

##### MyApiAnalyticsAspect.java

	package com.rohitThebest.aopdemo.aspect;
	
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

##### MyCloudLogAsyncAspect.java

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

- **Note :** We can use more than one advice in one aspect file, but the order of execution will be not defined. Therefore for manually making the ordering of the advice execution we keep different advices in different aspect files.

- **@Order()** : This annotation is used for ordering the execution of aspect files manually. 
	- Minimum the number, maximum is the pripority. For example : If the ordering is 1, 2, 4 -> 1 will execute first, then 2, then 4.
	- We can also use negative numbers for ordering. example : -43 , -23, 11
	- If a same number is give to aspects, then spring will decide which one to run first.

