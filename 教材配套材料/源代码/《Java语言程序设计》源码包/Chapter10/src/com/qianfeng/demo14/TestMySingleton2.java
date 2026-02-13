package com.qianfeng.demo14;

class MySingleton2 {
  //未进行实例化
  private static MySingleton2 instance;
  private MySingleton2() {
  }
  public static MySingleton2 getInstance() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (instance == null) {
      //线程在这里等待
      instance = new MySingleton2();
    }
    return instance;
  }
  public void show(){
    System.out.println("懒汉式单例模式");
  }
}
public class TestMySingleton2 {
  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      Thread t = new Thread(()->{
        System.out.println(MySingleton2.getInstance().hashCode());
      });
      t.start();
      System.out.println(MySingleton2.getInstance().hashCode());

    }
  }
}