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
