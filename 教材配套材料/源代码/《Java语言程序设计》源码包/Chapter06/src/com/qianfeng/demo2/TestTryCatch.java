package com.qianfeng.demo2;

public class TestTryCatch {
  public static void main(String[] args) {
    System.out.println("异常捕获开始...");
    try {
      int result = 10/0;
      System.out.println(result);
    } catch (ArithmeticException e) {
      System.out.println("捕获到了异常："+e);
      System.out.println("注意：除数不能为0！");
    }
    System.out.println("异常捕获结束...");
  }
}