package com.qianfeng.test1;

import java.util.ArrayList;
import java.util.List;

 class Cat {

	private String name;
	private char sex;
	private String color;
	private String status;
	//省略全参构造方法、getter()方法、setter()方法和 toString()方法

	public Cat(String name, char sex, String color, String status) {
		this.name = name;
		this.sex = sex;
		this.color = color;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}


public class TestArrayList {

	public static void main(String[] args) {
		String titles[] = {"姓名", "性别", "花色", "状况"};//定义标题数组
		//输出标题
		for (int i = 0; i < titles.length; i++) {
			System.out.print(titles[i] + "\t\t");
		}
		System.out.println();//换行
		System.out.println("————————————————————————————————————");
		List catList = new ArrayList();//创建流浪猫集合
		//创建流浪猫对象
		Cat cat1 = new Cat("小喵", '母', "橘猫", "收留");
		Cat cat2 = new Cat("小米", '公', "三花", "收留");
		Cat cat3 = new Cat("小咪", '母', "奶牛", "领养");
		//将流浪猫对象添加到集合中
		catList.add(cat1);
		catList.add(cat2);
		catList.add(cat3);
		//输出流浪猫信息
		for (int i = 0; i < catList.size(); i++) { // 遍历集合list
			Cat ele = (Cat) catList.get(i); // 获取集合list中的元素，并将其转换为String类型
			System.out.print(ele.getName() + "\t\t");
			System.out.print(ele.getSex() + "\t\t");
			System.out.print(ele.getColor() + "\t\t");
			System.out.print(ele.getStatus() + "\t\t");
			System.out.println();
		}
	}
}