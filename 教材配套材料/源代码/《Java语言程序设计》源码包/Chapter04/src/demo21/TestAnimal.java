package demo21;

class Animal {

  public void eat() {
    System.out.println("宠物吃宠物粮");
  }
}

public class Cat extends Animal {

  public void eat() {
    System.out.println("猫吃猫粮");
  }
}

public class Dog extends Animal {

  public void eat() {
    System.out.println("狗吃狗粮");
  }
}

public class TestAnimal {

  public static void main(String[] args) {
    Animal a1 = new Cat();
    Animal a2 = new Dog();
    a1.eat();
    a2.eat();
  }
}