package com.qianfeng.demo14;

import java.util.*;

public class TestKeySet {

	public static void main(String[] args) {
		Map map = new HashMap();                    // 创建HashMap集合
		map.put("cat1", "Lily");                    // 存入元素
		map.put("cat2", "Jack");
		map.put("cat3", "Jone");
		Set keySet = map.keySet();                    // 获取键的集合
		Iterator iterator = keySet.iterator();        // 获取迭代器对象
		while (iterator.hasNext()) {
			Object key = iterator.next();
			Object value = map.get(key);
			System.out.println(key + ":" + value);
		}
	}
}