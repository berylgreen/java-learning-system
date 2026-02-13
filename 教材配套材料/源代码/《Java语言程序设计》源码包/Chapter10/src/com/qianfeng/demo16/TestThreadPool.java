package com.qianfeng.demo16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    Runnable run = new Runnable() {
      public void run() {
        for (int i = 0; i < 3; i++) {
          System.out.println(Thread.currentThread().getName()
              + "执行了第" + i + "次");
        }
      }
    };
    es.submit(run);
    es.submit(run);
    es.shutdown();
  }
}