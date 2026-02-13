package demo20;

class Animal {
  private void beat() {
    System.out.println("心脏跳动...");
  }
  public void breath() {
    beat();
    System.out.println("呼吸中...");
  }
}
class Bird {
  // 将旧类组合到新类，作为新类的一个组合成分
  private Animal a;
  public Bird(Animal a) {
    this.a = a;
  }
  // 子类重新定义breath()方法
  public void breath() {
    // 复用Animal提供的breath()方法来实现Bird的breath()方法。
    a.breath();
  }
  public void fly() {
    System.out.println("我在轻快地飞翔...");
  }
}
class Dog {
  // 将旧类组合到新类，作为新类的一个组合成分
  private Animal a;
  public Dog(Animal a) {
    this.a = a;
  }
  // 重新定义一个自己的breath()方法
  public void breath() {
    // 复用Animal提供的breath()方法来实现Dog的breath()方法。
    a.breath();
  }
  public void run() {
    System.out.println("我在欢快地奔跑...");
  }
}
public class UseComposite {
  public static void main(String[] args) {
    // 需要显式创建被组合的对象
    Animal a1 = new Animal();
    Bird b = new Bird(a1);
    b.breath();
    b.fly();
    // 需要显式创建被组合的对象
    Animal a2 = new Animal();
    Dog d = new Dog(a2);
    d.breath();
    d.run();
  }
}