package com.qianfeng.demo8;

import java.util.*;
class Student {
}
public class TestTreeSetError {
	public static void main(String[] args) {
		TreeSet ts = new TreeSet();		// 创建TreeSet集合
		ts.add(new Student());			// 向集合中添加元素
	}
}