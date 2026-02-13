package demo22;

import demo21.Cat;
import demo21.Dog;

//不使用多态
class Feeder {

  //喂流浪狗的方法
  public void feed(Dog dog) {
    System.out.println("开始喂养。。。");
    dog.eat();
  }

  //喂流浪猫的方法
  public void feed(Cat cat) {
    System.out.println("开始喂养。。。");
    cat.eat();
  }
}

/*
class Feeder {
  public void feed(demo6.Animal a) {
    System.out.println("开始喂养。。。");
    a.eat();
  }
}
*/


public class TestNotPolymorphism {

  public static void main(String[] args) {
    Feeder f = new Feeder();
    Dog    d = new Dog();
    f.feed(d);//喂流浪狗
    Cat c = new Cat();
    f.feed(c);//喂流浪猫
  }
}