package demo8;

import demo5.Talk;
import demo6.Work;

public class Student implements Talk, Work {
  public String name;//定义姓名属性
  public Student(String name) { // 对姓名属性进行初始化
    this.name=name;
  }
  public void work() { // 重写work()方法
    System.out.println(name + "：同学开始学习...");
  }
  public void talk() { // 重写talk()方法
    System.out.println(name + "：老师好!");
  }
}