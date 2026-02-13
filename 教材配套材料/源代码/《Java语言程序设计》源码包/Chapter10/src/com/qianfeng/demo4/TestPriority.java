package com.qianfeng.demo4;

public class TestPriority {

  public static void main(String[] args) {
    // 创建SubThread1实例
    SubThread1 st1 = new SubThread1("优先级低的线程");
    SubThread1 st2 = new SubThread1("优先级高的线程");
    st1.setPriority(Thread.MIN_PRIORITY);    // 设置优先级
    st2.setPriority(Thread.MAX_PRIORITY);
    st1.start();                // 开启线程
    st2.start();
  }
}

class SubThread1 extends Thread {

  public SubThread1(String name) {
    super(name);
  }

  public void run() {              // 重写run()方法
    for (int i = 0; i < 10; i++) {
      if (i % 2 != 0) {
        System.out.println(Thread.
            currentThread().getName() + ":" + i);
      }
    }
  }
}