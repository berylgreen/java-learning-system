package com.qianfeng.demo24;

import java.util.*;
public class TestCollections2 {
	public static void main(String[] args) {
		List list = new ArrayList(5); 			// 创建集合对象
		list.add(35);						 	// 添加元素
		list.add(70);
		list.add(26);
		list.add(102);
		list.add(9);
		// 打印元素26在list集合中的索引
		System.out.println(Collections.binarySearch(list, 26));
		System.out.println("集合中的最大元素：" + Collections.max(list));
		System.out.println("集合中的最小元素：" + Collections.min(list));
		// 在集合list中，用元素35替换元素26
		Collections.replaceAll(list, 26, 35);
		// 打印集合中元素35出现的次数
		System.out.println(Collections.frequency(list, 35));
	}
}