package com.rohitThebest.aopdemo;

/*
 * For better understanding of this project go to :
 * https://github.com/rkumar0206/spring-AOP-demo
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.rohitThebest.aopdemo")
public class DemoConfig {
	
	
}
