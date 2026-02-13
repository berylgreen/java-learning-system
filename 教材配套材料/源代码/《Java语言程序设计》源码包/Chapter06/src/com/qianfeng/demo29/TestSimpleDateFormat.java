package com.qianfeng.demo29;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSimpleDateFormat {

	public static void main(String[] args) throws Exception {
		//创建 SimpleDateFormat 类的实例对象
		SimpleDateFormat sdf = new SimpleDateFormat();
		String date = sdf.format(new Date());
		System.out.println("默认格式：" + date);
		System.out.println("--------------------");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf2.format(new Date());
		System.out.println("自定义格式 1：" + date);
		System.out.println("--------------------");
		SimpleDateFormat sdf3 =
			new SimpleDateFormat("Gyyyy-MM-dd hh:mm:ss:SSS");
		date = sdf3.format(new Date());
		System.out.println("自定义格式 2：" + date);
	}
}