package demo1;

//猫类
class Cat {

  String name;
  double age;

  public void sayHello() {
    System.out.println("姓名：" + name + " 年龄：" + age);
  }

}

//测试类
public class TestCat {

  public static void main(String[] args) {
    Cat c = new Cat();
//    c.name = "小喵";
//    c.age = -1.5;  //设置age属性的值
//    c.sayHello();
  }
}