package com.qianfeng.demo11;

import java.util.*;
public class TestTreeSetSort {
	public static void main(String[] args) {
		// 第三步：创建TreeSet集合对象时,提供一个Comparator对象
		TreeSet tree = new TreeSet(new MyComparator());
		tree.add(new Student(140));
		tree.add(new Student(15));
		tree.add(new Student(11));
		System.out.println(tree);
	}
}
class Student {										// 定义Student类
	private Integer age;
	public Student(Integer age) {
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String toString() {
		return age + "";
	}
}
class MyComparator implements Comparator {		 	// 实现Comparator接口
	// 实现一个campare方法，判断对象是否是特定类的一个实例
	public int compare(Object o1, Object o2) {
		if (o1 instanceof Student & o2 instanceof Student) {
			Student s1 = (Student) o1;				// 强转为Student类型
			Student s2 = (Student) o2;
			if (s1.getAge() > s2.getAge()) {
				return -1;
			} else if (s1.getAge() < s2.getAge()) {
				return 1;
			}
		}
		return 0;
	}
}