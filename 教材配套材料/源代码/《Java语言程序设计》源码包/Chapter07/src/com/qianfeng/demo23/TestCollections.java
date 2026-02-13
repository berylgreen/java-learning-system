package com.qianfeng.demo23;

import java.util.*;
public class TestCollections {
	public static void main(String[] args) {
		List list = new ArrayList();	// 创建集合对象
		list.add(35);					// 添加元素
		list.add(70);
		list.add(26);
		list.add(102);
		list.add(9);
		System.out.println(list);		// 打印集合
		Collections.reverse(list);		// 反转集合
		System.out.println(list);
		Collections.shuffle(list);		// 随机排序
		System.out.println(list);
		Collections.sort(list);			// 按自然顺序排序
		System.out.println(list);
		// 将索引为1的元素和索引为3的元素交换位置
		Collections.swap(list, 1, 3);
		System.out.println(list);
	}
}