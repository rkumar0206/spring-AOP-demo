# spring-AOP-demo
- One of the key components of Spring Framework is the Aspect oriented programming (AOP) framework. Aspect-Oriented Programming entails breaking down program logic into distinct parts called so-called concerns. The functions that span multiple points of an application are called cross-cutting concerns and these cross-cutting concerns are conceptually separate from the application's business logic. There are various common good examples of aspects like logging, auditing, declarative transactions, security, caching, etc.
- For more go to https://www.tutorialspoint.com/spring/aop_with_spring.htm

### Aspect : 
- This is a module which has a set of APIs providing cross-cutting requirements. For example, a logging module would be called AOP aspect for logging. An application can have any number of aspects depending on the requirement.
- We mark a class as Aspect by using __@Aspect__ annotation

### Advice :
- This is the actual action to be taken either before or after the method execution. This is an actual piece of code that is invoked during the program execution by Spring AOP framework.
- There are five types of advice
  1. **@Before :** It is an advice type which ensures that an advice runs before the method execution.
  2. **@After :** It is an advice type which ensures that an advice runs after the method execution.
  3. **@AfterReturning :** Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.
  4. **@AfterThrowing :** Advice to be executed if a method exits by throwing an exception.
  5. **@Around:** Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.
  
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

---

#### JoinPoints
- A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution. Join point information is available in advice bodies by declaring a parameter of type org.aspectj.lang.JoinPoint.
- Here we will modify our one of the aspects and will try to see the JoinPoint in action.

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
		}
- We use JoinPoint as argument in advice.
- We get the MethodSignature using the *joinPoint.getSignature()* and downcast it to the MethodSignature type.
- For getting the values of arguument list of the method we can use *joinPoint.getArgs()*.

---

### Now Let's have a look on @AfterReturning()

- As defined above this advice is executed after the method has complted it's execution successfully without throwing any exception.

#### STEP 1 : Add method called *findAccount()* in AccountDAO.java which will return a list of Account type.

##### AccountDAO.java

	public List<Account> findAccounts() {
	
		List<Account> myAccounts = new ArrayList<>();
	
		Account account1 = new Account("Rohit", "Platinum");
		Account account2 = new Account("Mohit", "Silver");
		Account account3 = new Account("Sagar", "Gold");
	
		myAccounts.add(account1);
		myAccounts.add(account2);
		myAccounts.add(account3);
	
		return myAccounts;
		}

#### STEP 2 : Modify one of the aspects, Let's modify the MyDemoLoggingAspect and add a method with @AfterReturning annotation

##### MyDemoLoggingAspect.java

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
	}
	
- here the pointcut expression will match the method in AccountDAO with method name *findAccount*
- **returning** : It is used for getting the result returnes by the method
- Note : the name provided in the parameter for return should exactly match with returning i.e. *result*, this should be same as in returning

#### STEP 3 : Finally run the main app

##### AfterReturningDemoApp.java

	package com.rohitThebest.aopdemo;
	
	import java.util.List;
	
	import org.springframework.context.annotation.AnnotationConfigApplicationContext;
	
	import com.rohitThebest.aopdemo.dao.AccountDAO;
	
	public class AfterReturningDemoApp {
	
	       	public static void main(String[] args) {
	       
	       		// read spring config java class
	       		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
	       
	       		// get the bean from spring container
	       		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
	       
	       		List<Account> accounts = accountDao.findAccounts();
	       
	       		// display the accounts
	       
	       		System.out.println("\n\nMain program : AfterReturningDemoApp");
	       		System.out.println("-----");
	       
	       		System.out.println(accounts);
	       
	       		// close the context
	       		context.close();
	       	}
	       
	}

#### STEP 4 : Let's modify the data in between the main app and the target method

- We can post-process or modify the data in the aspect with advice @AfterReturning and then the modified data will be returned in the main app
- Modify the *afterReturningFindAccountsAdvice* method in AccountDAO

##### AccountDAO.java

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
- Here we are modifying the data by converting the name to uppercase.
- Run the main app and we can see that the results are modified.

### Using @AfterThrowing

- Advice to be executed if a method exits by throwing an exception.
- this advice can be used for
  - perform audinting on the exception
  - Log the exception
  - Notify team members via email
  - Encapsulate this functionality in AOP aspect for easy reuse

#### STEP 1 : Modify the findAccounts() method in AccountDAO.java

##### AccountDAO.java

	public List<Account> findAccounts(boolean tripWire) {
		
		// simulate an exception
		
		if (tripWire) {
			
			throw new RuntimeException("No soup for you!!!");
		}
		
		List<Account> myAccounts = new ArrayList<>();

		Account account1 = new Account("Rohit", "Platinum");
		Account account2 = new Account("Mohit", "Silver");
		Account account3 = new Account("Sagar", "Gold");

		myAccounts.add(account1);
		myAccounts.add(account2);
		myAccounts.add(account3);

		return myAccounts;
	}
- here if the boolean value *tripWire_* will be true then we will throw an exception

#### STEP 2 : Adding a method with annotation @AfterThrowing() in MyDemoLoggingAspect.java

##### MyDemoLoggingAspect.java

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
- here the pointcut expression will run advice on findAccount() method in AccountDAO.java
- *throwing* : this is used for catching the exception.
- The  @AfterThrowing() *throwing* parameter value shuld be exactly same as the Throwable parameter in *afterThrowingFindAccountAdvice* method.

#### STEP 3 : Run the app in AfterThrowingDemoApp.java

	package com.rohitThebest.aopdemo;
	
	import java.util.List;
	
	import org.springframework.context.annotation.AnnotationConfigApplicationContext;
	
	import com.rohitThebest.aopdemo.dao.AccountDAO;
	
	public class AfterThrowingDemoApp {
	
		public static void main(String[] args) {
	
			// read spring config java class
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
	
			// get the bean from spring container
			AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
	
			List<Account> accounts = null;
			
			try {
				
				// add a boolean flag to simulate exception
				boolean tripWire = true;
				accounts = accountDao.findAccounts(tripWire);
				
			}catch (Exception e) {
				
				System.out.println("\n\nMain program... caught exeption: " + e);
			}
	
			// display the accounts
	
			System.out.println("\n\nMain program : AfterThrowingDemoApp");
			System.out.println("-----");
	
			System.out.println(accounts);
	
			// close the context
			context.close();
		}
	
	}

- After running the program we can easily see the logged message from the @AfterThrowing() advice

### Using @After (finally)

- It is an advice type which ensures that an advice runs after the method execution, even after the exception occurs, that's why we can say it is like a finally block.

#### STEP 1 : Adding advice in MyDemoLoggingAspect.java

##### MyDemoLoggingAspect.java

	@After("execution(* com.rohitThebest.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @After (finally) on method: " + method);

		
	}

#### STEP 2 : Don't change the findAccount() method on AccountDAO.java

#### STEP 3 : Run the app in AfterFinallyDemoApp.java

##### AfterFinallyDemoApp.java

	package com.rohitThebest.aopdemo;
	
	/*
	 * For better understanding of this project go to :
	 * https://github.com/rkumar0206/spring-AOP-demo
	 */
	
	import java.util.List;
	
	import org.springframework.context.annotation.AnnotationConfigApplicationContext;
	
	import com.rohitThebest.aopdemo.dao.AccountDAO;
	
	public class AfterFinallyDemoApp {
	
		public static void main(String[] args) {
	
			// read spring config java class
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
	
			// get the bean from spring container
			AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
	
			List<Account> accounts = null;
			
			try {
				
				// add a boolean flag to simulate exception
				boolean tripWire = true;
				accounts = accountDao.findAccounts(tripWire);
				
			}catch (Exception e) {
				
				System.out.println("\n\nMain program... caught exeption: " + e);
			}
	
			// display the accounts
			System.out.println("\nMain program : AfterFinallyDemoApp");
			System.out.println("-----");
	
			System.out.println(accounts);
	
			// close the context
			context.close();
		}
	
	}

- change the value of tripWire to false and then true and observe the output
- the **@After()** will run in any situation irrespective of the exception


### Using @Around() 

- Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.

#### STEP 1 : Make service class

##### TrafficFortuneService.java
	package com.rohitThebest.aopdemo.service;
	
	import java.util.concurrent.TimeUnit;
	
	import org.springframework.stereotype.Component;
	
	@Component
	public class TrafficFortuneService {
	
		public String getFortune() {
			
			// simulate a delay
			
			try {
	
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// return a fortune
			
			return "Expect heavy traffic today";
		}
	}

- This service will just simulate a delay and will return a String.

#### STEP 2 : Making advice with *@Around()*
##### MyDemoLoggingAspect.java

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
		Object result = proceedingJoinPoint.proceed();
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display
		long duration = end - begin;
		System.out.println("\n====> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
- here we are calculating how must time is taken by the *getFortune()* method and displaying the duration
- after that returning the object to the target

#### STEP 3 : Running the main app

##### AroundDemoApp.java

	package com.rohitThebest.aopdemo;

	import org.springframework.context.annotation.AnnotationConfigApplicationContext;
	import com.rohitThebest.aopdemo.service.TrafficFortuneService;
	
	public class AroundDemoApp {
	
		public static void main(String[] args) {
	
			// read spring config java class
			AnnotationConfigApplicationContext context =
					new AnnotationConfigApplicationContext(DemoConfig.class);
	
			// get the bean from spring container
			TrafficFortuneService fortuneService = 
					context.getBean("trafficFortuneService", TrafficFortuneService.class);
	
			
			System.out.println("\nMain program: AroundDemoApp");
			
			System.out.println("Calling getFortune()");
			
			String data = fortuneService.getFortune();
			
			System.out.println("\nMy Fortune is : " + data);
			
			System.out.println("Finished");
			// close the context
			context.close();
		}
	}

