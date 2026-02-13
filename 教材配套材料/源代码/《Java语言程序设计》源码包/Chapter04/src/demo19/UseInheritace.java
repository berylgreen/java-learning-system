package demo19;

class Animal {

  private void beat() {
    System.out.println("心脏跳动...");
  }

  public void breath() {
    beat();
    System.out.println("呼吸中...");
  }
}

class Bird extends Animal {

  public void fly() {
    System.out.println("我轻快地飞翔...");
  }
}

class Dog extends Animal {

  public void run() {
    System.out.println("我欢快地奔跑...");
  }
}

public class UseInheritace {

  public static void main(String[] args) {
    Bird b = new Bird();
    b.breath();
    b.fly();
    Dog w = new Dog();
    w.breath();
    w.run();
  }
}