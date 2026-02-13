package demo1;


//图形抽象类
abstract class Graph {

  // abstract修饰抽象方法，只有声明，没有实现
  abstract public double getArea();
}

//矩形类继承图形类
class Rectangle extends Graph {

  private int width;
  private int height;

  //覆盖面积的抽象方法
  public double getArea() {
    return width * height;
  }
  //省略构造器

  public Rectangle(int width, int height) {
    this.width = width;
    this.height = height;
  }
}

//圆形类继承图形类
class Circle extends Graph {

  private int rids;

  //覆盖面积的抽象方法
  public double getArea() {
    return 3.14 * rids * rids;
  }

  public Circle(int rids) {
    this.rids = rids;
  }
}

//测试类
public class TestAbstract {

  public static void main(String[] args) {
    Graph rc = new Rectangle(3, 5);
    Graph ci = new Circle(4);
    System.out.println(rc.getArea());
    System.out.println(ci.getArea());
  }
}