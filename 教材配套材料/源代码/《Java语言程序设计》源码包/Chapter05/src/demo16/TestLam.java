package demo16;

import demo14.MathOne;
import demo15.ServiceOne;

public class TestLam {

  public static void main(String[] args) {
    TestLam testlam = new TestLam();
    //	实现MathOne接口，做加法运算,  有参数类型
    MathOne jiafa = (int a, int b) -> a + b;
    //	实现MathOne接口，做减法运算，无参数类型
    MathOne jianfa = (a, b) -> b - a;
    //	实现MathOne接口，做乘法运算，方法体外有大括号及返回值语句
    MathOne chengfa = (int a, int b) -> {
      return a * b;
    };
    //	实现MathOne接口，做除法运算，方法体外无大括号及返回值
    MathOne chufa = (int a, int b) -> b / a;
    //	实现ServiceOne接口，控制台打印
    ServiceOne service =
        (message) -> System.out.println("Hello," + message);
    service.printMessage("这是Java 8的新特性");
    service.printMessage("这是一个Lambda表达式");
    System.out.println("2+4=" + testlam.operate(2, 4, jiafa));
    System.out.println("4-2=" + testlam.operate(2, 4, jianfa));
    System.out.println("2*4=" + testlam.operate(2, 4, chengfa));
    System.out.println("4/2=" + testlam.operate(2, 4, chufa));
  }

  private int operate(int a, int b, MathOne mathOne) {
    return mathOne.operation(a, b);
  }
}