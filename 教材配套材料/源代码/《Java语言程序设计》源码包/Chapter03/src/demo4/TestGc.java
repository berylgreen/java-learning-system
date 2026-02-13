package demo4;

class Person {

  public void finalize() {
    System.out.println(this + "对象将被回收");
  }
}

public class TestGc {

  public static void main(String[] args) {
    Person p1 = new Person();
    Person p2 = new Person();
    // 让对象失去引用变量的引用
    p1 = null;
    p2 = null;
    // 通知JVM进行垃圾回收
    System.gc();
  }
}