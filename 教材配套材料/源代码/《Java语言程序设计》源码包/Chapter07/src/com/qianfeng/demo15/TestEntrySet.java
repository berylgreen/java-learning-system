package com.qianfeng.demo15;

import java.util.*;

public class TestEntrySet {
	public static void main(String[] args) {
		Map map = new HashMap(); 						// 创建HashMap集合
		map.put("stu1", "Lily"); 						// 存入元素
		map.put("stu2", "Jack");
		map.put("stu3", "Jone");
		Set entrySet = map.entrySet();
		Iterator iterator = entrySet.iterator(); 		// 获取迭代器对象
		while (iterator.hasNext()) {
			// 获取集合中键值对映射关系
			Map.Entry entry = (Map.Entry) iterator.next();
			Object key = entry.getKey();				// 获取关系中的键
			Object value = entry.getValue();			// 获取关系中的值
			System.out.println(key + ":" + value);
		}
	}
}