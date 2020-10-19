package com.wufuqiang.myextends;

public class B2 extends A2 {
	static{
		System.out.println("B2 static代码块");
	}

	{
		System.out.println("B2非static代码块");
	}

	public B2(){
		System.out.println("B2构造方法");
	}
}
