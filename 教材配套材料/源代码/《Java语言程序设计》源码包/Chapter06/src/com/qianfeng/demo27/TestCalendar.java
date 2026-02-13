package com.qianfeng.demo27;

import java.util.Calendar;

public class TestCalendar {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("年:" + c.get(Calendar.YEAR));
		System.out.println("月:" + c.get(Calendar.MONTH));
		System.out.println("日:" + c.get(Calendar.DAY_OF_MONTH));
		System.out.println("时:" + c.get(Calendar.HOUR_OF_DAY));
		System.out.println("分:" + c.get(Calendar.MINUTE));
		System.out.println("秒:" + c.get(Calendar.SECOND));
		System.out.println("毫秒:" + c.get(Calendar.MILLISECOND));
	}
}