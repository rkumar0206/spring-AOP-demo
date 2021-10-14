package com.rohitThebest.aopdemo;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

public class Account {

	private String name;
	private String level;
	
	public Account() {
	}
	
	public Account(String name, String level) {
		super();
		this.name = name;
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", level=" + level + "]";
	}
	
	
}
