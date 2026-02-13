package com.qianfeng.demo23;

public class TestRuntime {

  public static void main(String[] args) throws Exception {
    Runtime runtime = Runtime.getRuntime();
    System.out.println("处理器数量：" + runtime.availableProcessors());
    System.out.println("空闲内存数：" + runtime.freeMemory());
    System.out.println("总内存数：" + runtime.totalMemory());
    System.out.println("可用最大内存数：" + runtime.maxMemory());
    runtime.exec("notepad.exe");
  }
}