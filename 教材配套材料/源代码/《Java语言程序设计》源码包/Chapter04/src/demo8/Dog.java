package demo8;

import demo6.Animal;

public class Dog extends Animal {

  public Dog(String name, double age) {
    super(name, age); //子类构造方法调用父类构造方法
  }

  public void lookDoor() {
    System.out.println("狗会看门！");
  }
}