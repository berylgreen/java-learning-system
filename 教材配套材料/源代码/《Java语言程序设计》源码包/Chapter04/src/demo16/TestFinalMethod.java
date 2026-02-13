package demo16;

class Parent {

  // final关键字修饰方法
  public final void say() {
    System.out.println("final修饰say()方法");
  }
}

class Child extends Parent {

  // 重写父类方法
  public void say() {//报错
    System.out.println("重写父类say()方法");
  }
}

public class TestFinalMethod {

  public static void main(String[] args) {
    // 创建Child对象
    Child c = new Child();
    c.say();
  }
}