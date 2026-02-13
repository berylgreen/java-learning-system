package com.qianfeng.demo12;

import java.util.*;
public class TestHashMap {
	public static void main(String[] args) {
		Map map = new HashMap();				// 创建HashMap集合
		map.put("stu1", "Lily");				// 存入元素
		map.put("stu2", "Jack");
		map.put("stu3", "Jone");
		map.put(null, null);
		System.out.println(map.size());			// 打印集合长度
		System.out.println(map);				// 打印集合所有元素
		System.out.println(map.get("stu2"));	// 取出并打印键为stu2的值
	}
}