package demo10;

import java.util.Scanner;

public class TestSwitchCase {

  public static void main(String[] args) {
    System.out.println("请输入你要查看的流浪猫的种类");
    System.out.println("1.狸花猫");
    System.out.println("2.三花猫");
    System.out.println("3.奶牛猫");
    Scanner input  = new Scanner(System.in);
    int     choose = input.nextInt();
    switch (choose) {
      case 1:
        System.out.println("狸花猫1：小面-1岁5个月-母猫");
        System.out.println("狸花猫2：翠花-1岁6个月-母猫");
        break;
      case 2:
        System.out.println("三花猫1：丢丢-1岁10个月-公猫");
        System.out.println("三花猫2：黄连素-1岁8个月-母猫");
        break;
      case 32:
        System.out.println("奶牛猫1：学校流浪猫-1岁4个月-公猫");
        System.out.println("奶牛猫2：嘻嘻-1岁7个月-母猫");
        break;
      default:
        System.out.println("输入错误！");
    }
  }
}