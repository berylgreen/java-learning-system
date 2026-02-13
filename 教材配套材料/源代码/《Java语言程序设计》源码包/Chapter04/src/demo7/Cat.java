package demo7;

import demo6.Animal;

public class Cat extends Animal {

  public Cat(String name, double age) {
    super(name, age); //子类构造方法调用父类构造方法
  }

  public void catchMice() {
    System.out.println("猫会抓老鼠！");
  }
}