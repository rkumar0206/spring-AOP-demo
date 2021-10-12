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
