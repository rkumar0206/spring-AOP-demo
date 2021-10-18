package com.rohitThebest.aopdemo;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.rohitThebest.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TrafficFortuneService fortuneService = 
				context.getBean("trafficFortuneService", TrafficFortuneService.class);

		
		System.out.println("\nMain program: AroundHandleExceptionDemoApp");
		
		System.out.println("Calling getFortune()");
		
		boolean tripWire = true;
		String data = fortuneService.getFortune(tripWire);
		
		System.out.println("\nMy Fortune is : " + data);
		
		System.out.println("Finished");
		// close the context
		context.close();
	}

}
