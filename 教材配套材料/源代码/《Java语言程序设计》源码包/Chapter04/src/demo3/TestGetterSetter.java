package demo3;

//猫类
class Cat {

  private String name;
  private double age;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setAge(double age) {
    if (age > 0) {
      this.age = age;
    } else {
      System.out.println("设置错误：年龄不能为负数！");
    }
  }

  public double getAge() {
    return this.age;
  }
}

//测试类
public class TestGetterSetter {

  public static void main(String[] args) {
    Cat c = new Cat();
    // 调用 setter方法设置属性数据
    c.setName("喵喵");
    c.setAge(1.5);
    // 调用 getter方法获取属性数据
    String name = c.getName();
    double age  = c.getAge();
    System.out.println(name + "的年龄是" + age);
  }
}