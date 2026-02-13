package com.qianfeng.demo7;

import java.util.*;
public class TestTreeSet {
	public static void main(String[] args) {
		TreeSet tree = new TreeSet();				// 创建TreeSet集合
		tree.add(60);								// 添加元素
		tree.add(360);
		tree.add(120);
		System.out.println(tree);					// 打印集合
		System.out.println(tree.first());			// 打印集合中第一个元素
		// 打印集合中大于100小于500的元素
		System.out.println(tree.subSet(100, 500));
	}
}