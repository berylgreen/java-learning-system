package com.qianfeng.demo6;

public class TestYield {
  public static void main(String[] args) {
    SubThread3 st = new SubThread3(); 	// 创建SubThread3实例
    new Thread(st, "线程1").start(); 	// 创建并开启线程
    new Thread(st, "线程2").start();
  }
}
class SubThread3 implements Runnable {
  public void run() { 					// 重写run()方法
    for (int i = 1; i <= 6; i++) {
      System.out.println(Thread.
          currentThread().getName() + ":" + i);
      if (i % 3 == 0) {
        Thread.yield();
      }
    }
  }
}