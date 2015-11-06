package com.anemortalkid.data;

public class LongVDouble {

	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE > Long.MAX_VALUE);
		System.out.println(Double.MAX_VALUE < Long.MAX_VALUE);
		try {
			Double wee = double.class.newInstance();
			System.out.println(wee.NEGATIVE_INFINITY);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
