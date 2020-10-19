package com.wufuqiang.myextends;

public class MainDemo {
	public static void main(String[] args) {
		//1、如果调用的是方法，则JVM会将该方法和对象的内存地址绑定
		//2、如果调用的是属性，则没有动态绑定机制，在哪里调用，就返回对应值
		A a = new B();
		System.out.println(a.sum());  //20+20
		System.out.println(a.sum1()); //10+10

	}
}
