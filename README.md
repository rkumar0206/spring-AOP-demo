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
  2. @After
  
 
