package demo6;

public class Animal {
  public String name;
  double age;

  public Animal(String name, double age) {
  }

  //省略构造器和getter/setter方法
  public void sayHello() {
    System.out.println("姓名：" + this.name + " 年龄：" + this.age);
  }
}