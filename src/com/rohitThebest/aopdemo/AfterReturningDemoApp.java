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
