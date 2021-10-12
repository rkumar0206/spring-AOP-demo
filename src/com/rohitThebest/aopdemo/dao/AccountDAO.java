package com.rohitThebest.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.rohitThebest.aopdemo.Account;


@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;

	
	public String getName() {
		
		System.out.println(getClass() + ": getName()");
		return name;
	}

	public void setName(String name) {
		
		System.out.println(getClass() + ": setName()");
		this.name = name;
	}

	public String getServiceCode() {
		
		System.out.println(getClass() + ": getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount() {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT");
	}

	public void addAccount(Account account) {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT with 1 parameter");
	}

	
	public void addAccount(Account account, boolean vipFlag) {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT with 2 parameters");
	}
	
	
	public void addSomething() {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING SOMETHING");
	}
	
	public boolean doWork() {
		
		System.out.println(getClass() + ": Doing my DB work: doWork()");
		return false;
	}
	
	
}
