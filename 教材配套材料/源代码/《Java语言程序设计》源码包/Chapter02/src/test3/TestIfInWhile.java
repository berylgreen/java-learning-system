package test3;

import java.util.Scanner;

public class TestIfInWhile {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int     flag  = 3;//控制循环次数
    for (int i = 0; i < 3; i++) {
      System.out.print("请输入用户名：");
      String uName = input.nextLine();
      System.out.print("请请输入密码：");
      String pwd = input.nextLine();
      //比较账号密码
      if ("admin".equals(uName) && "123456".equals(pwd)) {
        System.out.println("登录成功！");
      } else {
        System.out.println("登录失败，你还有" + (2 - i) + "机会");
      }
    }
  }
}