package demo9;

import demo7.Cat;
import demo6.Animal;
import demo8.Dog;

public class TestInheritance {

  public static void main(String[] args) {
    Animal a = new Animal("Tom", 10);
    System.out.println(a.name);
    a.sayHello();
    Cat c = new Cat("小喵", 1.5);
    System.out.println(c.name);
    c.sayHello();
    c.catchMice();
    Dog d = new Dog("小汪", 3);
    System.out.println(d.name);
    d.sayHello();
    d.lookDoor();
  }
}