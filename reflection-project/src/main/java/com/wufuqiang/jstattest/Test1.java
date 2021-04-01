package com.wufuqiang.jstattest;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test1 {
	public static void main(String[] args) {
		//jstat -gcutil pid time
		while (true){
			new Person(String.valueOf(new Random().nextInt(999999)),new Random().nextInt(120));
		}
		Executors.newFixedThreadPool(5)
	}
}

class Person{
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
