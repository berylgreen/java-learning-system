package com.qianfeng.demo5;

import java.util.ArrayList;
import java.util.Collection;

public class TestForeach {
	public static void main(String[] args) {
		Collection coll = new ArrayList();		// 创建集合
		coll.add("red");						// 向集合中添加元素
		coll.add("yellow");
		coll.add("blue");
		for (Object o : coll) {					// 用foreach遍历集合中元素
			System.out.println(o);				// 打印集合中取出来的每个元素
		}
	}
}