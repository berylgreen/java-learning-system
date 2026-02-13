package com.qianfeng.demo5;

public class TestThrow {

  public static int div(int a, int b) {
    // 抛出异常的实例对象
    if (0 == b) {
      throw new ArithmeticException("错误：除数不能为0！");
    }
    return a / b;
  }

  public static void main(String[] args) {
    try {
      int val = div(10, 0);
      System.out.println(val);
    } catch (ArithmeticException e) {
      System.out.println(e);
    }
  }
}