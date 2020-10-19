package com.wufuqiang.myextends;

public class A2 {
	static{
		System.out.println("A2 static代码块");
	}



	public  A2(){
		System.out.println("A2构造方法");
	}

	{
		System.out.println("A2 非static代码块");
	}
}
