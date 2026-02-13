package demo12;

class Outter {

  // 定义类静态成员
  private static String name = "Outter";
  private static int    count;

  public void say() {
    // 定义局部内部类
    class Inner {

      // 定义类成员
      public String name = "Outter.Inner";

      // 定义类成员
      public void say() {
        // 内部类成员方法中访问外部类私有成员变量
        System.out.print(Outter.name);
        System.out.println(":" + count);
      }
    }
    // 创建局部内部类对象
    Inner obj = new Inner();
    obj.say();
  }
}

public class TestMethInnerClass {

  public static void main(String[] args) {
    // 创建外部类对象
    Outter obj = new Outter();
    obj.say();
  }
}