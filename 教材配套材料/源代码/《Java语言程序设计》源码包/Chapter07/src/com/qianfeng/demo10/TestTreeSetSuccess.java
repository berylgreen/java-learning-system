package com.qianfeng.demo10;

import java.util.*;
class Student implements Comparable{
	public int compareTo(Object o) {	//重写compareTo()方法
		return 1;						//总是返回1
	}
}

public class TestTreeSetSuccess {
	public static void main(String[] args) {
		TreeSet ts = new TreeSet();		// 创建TreeSet集合
		ts.add(new Student());			// 向集合中添加元素
		ts.add(new Student());
		System.out.println(ts);			// 打印集合
	}
}