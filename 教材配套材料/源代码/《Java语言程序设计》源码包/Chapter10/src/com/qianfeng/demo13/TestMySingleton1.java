package com.qianfeng.demo13;

class MySingleton1 {

  //创建MySingleton的一个对象
  private static MySingleton1 instance = new MySingleton1();

  //私有化构造方法，避免被外部创建实例
  private MySingleton1() {
  }

  //获取唯一可用的对象
  public static MySingleton1 getInstance() {
    return instance;
  }

  //该单例类提供的方法
  public void show() {
    System.out.println("饿汉式单例模式");
  }
}

public class TestMySingleton1 {

  public static void main(String[] args) {
    //MySingleton1 object = new MySingleton1();
    //获取唯一可用的对象
    MySingleton1 object2 = MySingleton1.getInstance();
    //显示消息
    object2.show();
  }

}