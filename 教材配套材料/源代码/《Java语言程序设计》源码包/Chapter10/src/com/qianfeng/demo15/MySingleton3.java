package com.qianfeng.demo15;

class MySingleton3 {
  private static MySingleton3 instance;
  private MySingleton3 () {}
  public static MySingleton3 getInstance() {
    if (instance == null) {
      synchronized(MySingleton3.class) { // 注意这里是类级别的锁
        if (instance == null) {       // 这里的检测避免多线程并发时多次创建对象
          instance = new MySingleton3();
        }
      }
    }
    return instance;
  }
}