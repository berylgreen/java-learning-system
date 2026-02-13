package demo15;

// 使用final关键字修饰类
public final class Parent {

}

// 继承final类
class Child extends Parent {//报错
}

public class TestFinalClass {

  public static void main(String[] args) {
    // 创建Child对象
    Child c = new Child();
  }
}