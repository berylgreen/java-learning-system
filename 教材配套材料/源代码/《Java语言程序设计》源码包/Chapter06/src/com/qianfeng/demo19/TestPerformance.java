package com.qianfeng.demo19;

public class TestPerformance {

  public static void main(String[] args) {
    String        string    = "";
    long          startTime = 0L;
    long          endTime   = 0L;
    StringBuffer  buffer    = new StringBuffer("");
    StringBuilder builder   = new StringBuilder("");
    startTime = System.currentTimeMillis(); //获取当前系统的时间毫秒数
    for (int i = 0; i < 20000; i++) {
      string = string + i; //将字符串修改 2 万次
    }
    endTime = System.currentTimeMillis();
    System.out.println("String 的执行时间：" +
        (endTime - startTime) + "毫秒");
    startTime = System.currentTimeMillis();
    for (int i = 0; i < 20000; i++) {
      buffer.append(String.valueOf(i));
    }
    endTime = System.currentTimeMillis();
    System.out.println("StringBuffer 的执行时间：" + (endTime - startTime) + "毫秒");
    startTime = System.currentTimeMillis();
    for (int i = 0; i < 20000; i++) {
      builder.append(String.valueOf(i));
    }
    endTime = System.currentTimeMillis();
    System.out.println("StringBuilder 的执行时间：" +
        (endTime - startTime) + "毫秒");
  }
}