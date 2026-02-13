package com.qianfeng.demo1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestList {

  public static void main(String[] args) {
    List list1 = new ArrayList();
    list1.add("小咪");
    list1.add(1.5);
    List list2 = new LinkedList();
    list2.add("小喵");
    list2.add(2);
    System.out.println("list1：" + list1);
    System.out.println("list2：" + list2);
    list1.add(0, "小猫资料");
    System.out.println("在list1索引为0的位置添加元素”小猫资料“：" + list1);
    System.out.println("list1元素的个数：" + list1.size());
    list1.addAll(list2);
    System.out.println("添加list2后的list1：" + list1);
    System.out.println("list1索引为0位置的元素：" + list1.get(0));
    list1.set(0, "哈哈");
    System.out.println("将索引为0的位置的元素修改为“哈哈”后的list1：" + list1);
    System.out.println("list1第一次出现“哈哈”的索引：" + list1.indexOf("哈哈"));
    list1.remove(1);
    System.out.println("移除索引1位置的元素后的list1：" + list1);
    list1.clear();
    System.out.println("清空元素后的list1：" + list1);
  }
}