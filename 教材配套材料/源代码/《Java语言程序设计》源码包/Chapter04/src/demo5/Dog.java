package demo5;

class Dog {

  private String name;
  private double age;

  //省略构造器和getter/setter方法
  public void sayHello() {
    System.out.println("姓名：" + this.name + " 年龄：" + this.age);
  }

  public void lookDoor() {
    System.out.println("狗会看门！");
  }
}