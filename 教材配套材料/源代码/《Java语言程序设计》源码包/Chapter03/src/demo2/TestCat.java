package demo2;

import demo1.Cat;

public class TestCat {

  public static void main(String[] args) {
    Cat c1 = new Cat(); //创建Cat类的对象c1
    Cat c2 = new Cat(); //创建Cat类的对象c2
    c1.name = "小喵"; //为c1的name属性赋值
    c1.sex = "女";//为c1的sex属性赋值
    c1.color = "三花";//为c1的color属性赋值
    c1.age = 1.5;//为c1的age属性赋值
    String name = c1.name;
    System.out.println(name);
    c1.sayHello();
    c2.sayHello();
    c2 = c1;
    c2.sayHello();
  }
}