package com.qianfeng.demo9;

import java.util.*;

public class TestTreeSetError2 {

	public static void main(String[] args) {
		TreeSet ts = new TreeSet();        // 创建TreeSet集合
		ts.add(100);                    // 向集合中添加元素
		ts.add(new Date());
	}
}