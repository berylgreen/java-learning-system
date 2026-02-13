package com.qianfeng.demo28;

import java.text.DateFormat;
import java.util.*;

public class TestDateFormat {

	public static void main(String[] args) {
		DateFormat df1 = DateFormat.getDateInstance();
		DateFormat df2 = DateFormat.getTimeInstance();
		DateFormat df3 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD,
			new Locale("zh", "CN"));
		DateFormat df4 = DateFormat.getTimeInstance(DateFormat.ERA_FIELD,
			new Locale("zh", "CN"));
		System.out.println("data：" + df1.format(new Date()));
		System.out.println("time：" + df2.format(new Date()));
		System.out.println("----------------------");
		System.out.println("data：" + df3.format(new Date()));
		System.out.println("time：" + df4.format(new Date()));
	}
}