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
