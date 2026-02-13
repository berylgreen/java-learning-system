package demo5;

import java.util.Scanner;

public class TestScNext {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("使用next()方法接收数据：");
    String str1 = input.next();
    String str2 = input.next();
    System.out.println(str1);
    System.out.println(str2);
  }
}