package demo4;

class Cat {
  private String name;
  private double age;

  //省略构造器和getter/setter方法
  public void sayHello() {
    System.out.println("姓名：" + this.name + " 年龄：" + this.age);
  }
  public void catchMice(){
    System.out.println("猫会抓老鼠！");
  }
}