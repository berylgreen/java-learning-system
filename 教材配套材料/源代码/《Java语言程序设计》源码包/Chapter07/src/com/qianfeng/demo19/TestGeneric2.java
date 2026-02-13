package com.qianfeng.demo19;

import java.util.*;
public class TestGeneric2 {
	public static void main(String[] args) {
		List<?> list = null;		// 声明泛型为?的List
		list = new ArrayList<String>();
		list = new ArrayList<Integer>();
		// list.add(3);				// 编译时报错
		list.add(null);				//添加元素null
		System.out.println(list);
		List<Integer> l1 = new ArrayList<Integer>();
		List<String> l2 = new ArrayList<String>();
		l1.add(1000);
		l2.add("phone");
		read(l1);
		read(l2);
	}
	static void read(List<?> list) {
		for (Object o : list) {
			System.out.println(o);
		}
	}
}