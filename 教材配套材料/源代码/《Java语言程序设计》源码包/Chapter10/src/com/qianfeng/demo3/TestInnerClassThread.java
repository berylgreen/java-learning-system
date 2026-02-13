package com.qianfeng.demo3;

public class TestInnerClassThread {
  public static void main(String[] args) {
    for (int i = 0; i < 50; i++) {
      System.out.println("玩游戏" + i);
      if (i == 10) {
        Thread th1 = new Thread(new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 50; i++) {
              System.out.println("播放音乐" + i);
            }
          }
        });
        th1.start();
      }
    }
  }
}