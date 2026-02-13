package com.qianfeng.demo4;

public class TestThrows {

  // 声明抛出异常，本方法中可以不处理异常
  public static int div(int a, int b) throws ArithmeticException {
    return a / b;
  }

  public static void main(String[] args) {
    try {
      // 因为方法中声明抛出异常，不管是否发生异常，都必须处理
      int val = div(10, 0);
      System.out.println(val);
    } catch (ArithmeticException e) {
      System.out.println(e);
    }
  }
}