package demo7;

public class TestStaticBlock {
  static String name;
  static {
    System.out.println(name + "静态代码块");
  }
  {
    System.out.println(name + "非静态代码块");
  }
  public TestStaticBlock(String a) {
    name = a;
    System.out.println(name + "构造方法");
  }
  public void method() {
    System.out.println(name + "成员方法");
  }
  public static void main(String[] args) {
    TestStaticBlock s1;
    TestStaticBlock s2 = new TestStaticBlock("s2");
    TestStaticBlock s3 = new TestStaticBlock("s3");
    s3.method();// 只有调用的时候才会运行
  }
}