package com.rohitThebest.aopdemo.dao;

import org.springframework.stereotype.Component;


@Component
public class AccountDAO {
	
	public void addAccount() {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT");
	}
}
