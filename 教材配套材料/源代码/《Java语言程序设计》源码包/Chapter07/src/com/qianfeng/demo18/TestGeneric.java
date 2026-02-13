package com.qianfeng.demo18;

import java.util.ArrayList;
public class TestGeneric {
	public static void main(String[] args) {
		// 创建集合对象，并限定只能添加String类型的元素
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");					// 添加元素
		list.add("b");
		list.add("c");
		System.out.println(list);		// 打印集合
	}
}