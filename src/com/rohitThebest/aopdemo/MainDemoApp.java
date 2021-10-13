package com.rohitThebest.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rohitThebest.aopdemo.dao.AccountDAO;
import com.rohitThebest.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);

		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// call the business method
		//accountDao.addAccount();
		//accountDao.addAccount(new Account());
		accountDao.addAccount(new Account("Rohit", "platinum"), true);
		accountDao.doWork();
		//accountDao.addSomething();

		// call the account dao getter / setter methods
		
		accountDao.setName("fooName");
		accountDao.setServiceCode("fooServiceCode");
		accountDao.getName();
		accountDao.getServiceCode();
		
		// -----------------
		
		//membershipDAO.addAccount();		
		//membershipDAO.addAnything();
		membershipDAO.gotToSleep();
		
		// close the context
		context.close();
	}

}
