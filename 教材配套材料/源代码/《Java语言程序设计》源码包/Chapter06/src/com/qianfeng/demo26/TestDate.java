package com.qianfeng.demo26;

import java.util.Date;

public class TestDate {

	public static void main(String[] args) {
		Date date1 = new Date();
		System.out.println(date1);
		Date date2 = new Date(999999999999L);
		System.out.println(date2);
	}
}