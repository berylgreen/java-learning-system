package demo17;
//无参无返回的函数式接口
interface Plane {
  void fly();
}
//有参、有返回值的函数式接口
interface Phone {
  String call(String phoneNum);
}
public class TestFunInterfaces {
  public static void main(String[] args) {
    //分别两个函数式接口进行测试
    planeFly(() -> System.out.println("无参、无返回值的函数式接口调用"));
    callPhone("13000000000", phone -> "phoneNUm");
  }
  //创建一个飞机飞的方法，并传入接口对象作为参数
  private static void planeFly(Plane plane) {
    plane.fly();
  }
  //创建一个打电话的方法，并传入两个String类型以及接口Phone类型的参数
  private static void callPhone(String phoneNum, Phone phone) {
    System.out.println("拨打电话" + phone.call(phoneNum));
  }
}