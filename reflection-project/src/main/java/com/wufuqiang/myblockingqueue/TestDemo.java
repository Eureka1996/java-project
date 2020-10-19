package com.wufuqiang.myblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestDemo {
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
		queue.put(10);
		queue.put(10);
		queue.put(10);
		System.out.println(queue.size());
		System.out.println("wufuqiang");
	}
}
