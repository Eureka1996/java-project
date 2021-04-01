package com.wufuqiang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
	public static void main(String[] args) {
		String peakMemory = "0.5555Bfff";
		String regex = "^(\\d+\\.?\\d*)(\\D+)$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(peakMemory);
		if(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}else{
			System.out.println("没有匹配的.");
		}

	}
}
