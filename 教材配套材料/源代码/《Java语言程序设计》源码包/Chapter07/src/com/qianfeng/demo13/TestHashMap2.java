package com.qianfeng.demo13;

import java.util.*;
public class TestHashMap2 {
	public static void main(String[] args) {
		Map map = new HashMap();				// 创建HashMap集合
		map.put("cat1", "Lily");				// 存入元素
		map.put("cat2", "Jack");
		map.put("cat3", "Jone");
		map.put("cat3", "Lily");
		System.out.println(map);				// 打印集合所有元素
	}
}