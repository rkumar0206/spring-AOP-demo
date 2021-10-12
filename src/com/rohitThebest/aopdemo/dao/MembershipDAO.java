package com.rohitThebest.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING MEMBERSHIP ACCOUNT");
	}
	
	public boolean addAnything() {
		
		System.out.println(getClass() + ": Doing my DB work: ADDING MEMBERSHIP Anything ACCOUNT");
		return true;
	}
	
	public void gotToSleep() {
		
		System.out.println(getClass() + ": Sleeping");
	}
}
