package test1;

//流浪猫类
class Cat {
  String name;
  String area;
  public Cat(String name, String area) {
    this.name = name;
    this.area = area;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
}
//用户类
class User {
  String name;
  String area;
  public void adopt(Cat cat) {
    System.out.println(name+"领养了"+cat.name);
  }
  public User(String name, String area) {
    this.name = name;
    this.area = area;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
}
//测试类
public class TestCatAdopt {
  public static void main(String[] args) {
    //创建流浪猫对象
    Cat cat1 = new Cat("喵喵","北京");
    Cat cat2 = new Cat("大壮","上海");
    //创建用户对象
    User user = new User("Tom","上海");
    if(user.getArea().equals(cat1.getArea())){
      user.adopt(cat1);
    }else{
      System.out.println("非同城不能领养！");
    }
    if(user.getArea().equals(cat2.getArea())){
      user.adopt(cat2);
    }else{
      System.out.println("非同城不能领养！");
    }
  }
}