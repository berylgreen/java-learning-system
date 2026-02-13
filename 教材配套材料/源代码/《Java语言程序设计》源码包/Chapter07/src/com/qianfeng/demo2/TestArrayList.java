package com.qianfeng.demo2;

import java.util.ArrayList;
import java.util.Date;

public class TestArrayList {

  public static void main(String[] args) {
    ArrayList arr = new ArrayList(); //创建 ArrayList 集合
    arr.add(new Date()); //向集合中添加元素
    arr.add("幸运宠物");
    System.out.println(arr.size()); //输出集合元素的个数
    System.out.println(arr.get(0)); //获取并输出集合中指定索引的元素
  }
}