package com.qianfeng.demo3;

import java.util.LinkedList;

public class TestLinkedList {

  public static void main(String[] args) {
    LinkedList<String> catList = new LinkedList<String>();
    catList.add("喵喵");
    catList.add("大壮");
    catList.add("Tom");
    catList.add("哈哈");
    System.out.println(catList);
    catList.addFirst("二胖");
    System.out.println(catList);
    catList.addLast("花花");
    System.out.println(catList);
    catList.removeFirst();
    System.out.println(catList);
    System.out.println("移除第一个元素" + catList.removeFirst());
    System.out.println(catList);
    System.out.println("第一个元素：" + catList.getFirst());
    System.out.println("最后一个元素：" + catList.getLast());
    System.out.println("移除最后一个元素：" + catList.removeLast());
    System.out.println(catList);
  }
}