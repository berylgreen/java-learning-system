package demo7;

import demo5.Talk;
import demo6.Work;

public class Teacher implements Talk, Work {
  public String name;//定义姓名属性
  public Teacher(String name) { // 对姓名属性进行初始化
    this.name=name;
  }
  public void work() { // 重写work()方法
    System.out.println(name + "：老师开始上课...");
  }
  public void talk() { // 重写talk()方法
    System.out.println(name + "：同学们好!");
  }
}