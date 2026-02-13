package com.qianfeng.demo1;

public class TestThread {
  // 播放音乐的线程类
  static class MusicThread extends Thread {
    //模拟播放的过程
    private int playTime = 50;
    public void run() {
      for (int i = 0; i < playTime; i++) {
        System.out.println("播放音乐" + i);
      }
    }
  }
  public static void main(String[] args) {
    // 主线程：运行游戏
    for (int i = 0; i < 50; i++) {
      System.out.println("打游戏" + i);
      if (i == 10) {
        // 创建播放音乐线程
        MusicThread musicThread = new MusicThread();
        musicThread.start();
      }
    }
  }
}