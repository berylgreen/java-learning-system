package demo2;

class Cat {

  String name;
  private double age;

  public void setAge(double age) {
    if (age > 0) {
      this.age = age;
    } else {
      System.out.println("设置错误：年龄不能为负数！");
    }
  }

  public void sayHello() {
    System.out.println("姓名：" + this.name + " 年龄：" + this.age);
  }
}

public class TestEncapsulation {

  public static void main(String[] args) {
    Cat c = new Cat();
    c.name = "小喵";
//    c.age = -1.5; //设置age属性的值
    c.sayHello();
  }
}