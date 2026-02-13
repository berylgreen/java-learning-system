package com.qianfeng.demo16;

import java.util.*;
public class TestLinkedHashMap {
	public static void main(String[] args) {
		Map map = new LinkedHashMap();			// 创建LinkedHashMap集合
		map.put("2", "yellow");					// 添加元素
		map.put("1", "red");
		map.put("3", "blue");
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			// 获取集合中键值对映射关系
			Map.Entry entry = (Map.Entry) iterator.next();
			Object key = entry.getKey(); 		// 获取关系中的键
			Object value = entry.getValue(); 	// 获取关系中的值
			System.out.println(key + ":" + value);
		}
	}
}