package demo11;

class Outter {

  // 定义类静态成员
  private static String name = "Outter";
  private static int    count;

  // 定义静态内部类
  static class Inner {

    // 定义类静态成员
    public static String name = "Outter.Inner";

    // 定义类成员
    public void say() {
      // 内部类成员方法中访问外部类私有成员变量
      System.out.print(Outter.name);
      System.out.println(":" + count);
    }
  }
}

public class TestStaticInnerClass {

  public static void main(String[] args) {
// 访问静态内部类的静态成员
    String str = Outter.Inner.name;
    System.out.println(str);
// 创建静态内部类对象
    Outter.Inner obj = new Outter.Inner();
    obj.say();
  }
}