package demo3;

class Cat {
  String name;    //name属性

  public Cat() {
    System.out.println("无参构造方法被调用了...");
  }
  public Cat(String name) {
    this();
    System.out.println("有参构造方法被调用了...");
  }
}
public class TestThis {
  public static void main(String[] args) {
    Cat cat = new Cat("Tom");
  }
}