package demo12;

import java.util.Scanner;

public class TestIfInIf {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("请输入献血者的年龄");
    int age = input.nextInt();
    if (age >= 18 && age <= 55) {
      System.out.println("请输入献血者的性别：");
      String sex = input.next();
      System.out.println("请输入献血者的体重（单位：千克）：");
      double weight = input.nextDouble();
      if (sex.equals("男")) {
        if (weight >= 50) {
          System.out.println("可以献血！");
        } else {
          System.out.println("不可献血！");
        }
      } else if (sex.equals("女")) {
        if (weight >= 45) {
          System.out.println("可以献血！");
        } else {
          System.out.println("不可献血！");
        }
      } else {
        System.out.println("请输入正确的性别！");
      }
    } else {
      System.out.println("不符合献血年龄要求！");
    }
  }
}