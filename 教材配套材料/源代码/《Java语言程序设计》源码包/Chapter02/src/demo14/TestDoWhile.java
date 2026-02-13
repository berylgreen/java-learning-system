package demo14;

import java.util.Scanner;
public class TestDoWhile {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String uName;
    String pWord;
    do{
      System.out.println("请输入账号：");
      uName = input.nextLine();
      System.out.println("请输入密码：");
      pWord = input.nextLine();
    }while (!("admin".equals(uName)) || !("123456".equals(pWord)));
    System.out.println("登录成功！");
  }
}