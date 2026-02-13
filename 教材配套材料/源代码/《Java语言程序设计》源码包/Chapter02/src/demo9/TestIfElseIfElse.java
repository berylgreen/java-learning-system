package demo9;

import java.util.Scanner;

public class TestIfElseIfElse {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入您的体重(单位：千克)");
    double weight = sc.nextDouble();
    System.out.println("请输入您的身高(单位：米)");
    double height = sc.nextDouble();
    double BMI    = weight / (height * height);
    System.out.println("您的BMI指数是：" + BMI);
    if (BMI < 18.5) {
      System.out.println("您的体重过轻！");
    } else if (BMI >= 18.5 && BMI < 24) {
      System.out.println("您的体重正常！");
    } else if (BMI >= 24 && BMI < 28) {
      System.out.println("您的体重过重！");
    } else {
      System.out.println("您的体重过于肥胖！");
    }
  }
}