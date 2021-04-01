package com.wufuqiang.enumdemo;

public class EnumDemo01 {
	public static void main(String[] args) {
		double memory = 98.45;
		MemoryUnits unitEnum = MemoryUnits.valueOf("ZB");
//		System.out.println(String.format("%.03f*%d=%d",memory, unitEnum.getConstant(),(long)memory*unitEnum.getConstant()));
	}
}



enum MemoryUnits{
	B("B",1),
	KB("KB",1024),
	MB("MB",1024*1024),
	GB("GB",1024*1024*1024),
	TB("TB",1024*1024*1024*1024);

	private String unit;
	private long constant;
	private MemoryUnits(String unit,long constant){
		this.unit = unit;
		this.constant = constant;
	}

	public String getUnit() {
		return unit;
	}

	public long getConstant() {
		return constant;
	}
}