package com.qianfeng.demo8;

public class TestBackThread {
  public static void main(String[] args) {
    // 创建SubThread5实例
    SubThread5 st1 = new SubThread5("新线程");
    st1.setDaemon(true);
    st1.start();
    for (int i = 0; i < 2; i++) {
      System.out.println(Thread.
          currentThread().getName() + ":" + i);
    }
  }
}
class SubThread5 extends Thread {
  public SubThread5(String name) {
    super(name);
  }
  public void run() {			// 重写run()方法
    for (int i = 0; i < 1000; i++) {
      if (i % 2 != 0) {
        System.out.println(Thread.
            currentThread().getName() + ":" + i);
      }
    }
  }
}