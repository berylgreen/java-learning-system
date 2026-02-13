package com.qianfeng.demo20;

import java.util.*;
public class TestGeneric3 {
	public static void main(String[] args) {
		List<? extends Person> list = null;
		// list=new ArrayList<String>(); 	报编译时异常
		list = new ArrayList<Person>();
		list = new ArrayList<Man>();
		List<? super Man> list2 = null;
		// list=new ArrayList<String>(); 	报编译时异常
		list2 = new ArrayList<Person>();
		list2 = new ArrayList<Man>();
	}
}
class Person {
}
class Man extends Person {
}