package com.qianfeng.pratice;

import java.util.Scanner;
public class ShareRedBag {
  public static void main(String[] args) {
    System.out.println("----------拼手气红包----------");
    Scanner sc = new Scanner(System.in);
    System.out.print("请输入群成员个数：");
    int memberIndex = sc.nextInt();
    String[] name = new String[memberIndex];
    for (int i=0;i<memberIndex;i++){
      System.out.print("请输入群成员昵称：");
      name[i] = sc.next();
    }
    System.out.print("请输入所发红包的总金额：");
    double money= sc.nextDouble();
    System.out.print("请输入所发红包的个数：");
    int num=sc.nextInt();
    if (money / num == 0.01) {
      for (int i = 0; i <memberIndex; i++) {
        System.out.println(name[i] + "抢到红包红包0.01元");
      }

    } else if (money / num < 0.01) {
      System.out.println("金额过小或个数过大，不合理！，请重新输入！");
    } else {
      RedBag bag=new RedBag(num,money);
      System.out.println("抢红包开始，红包总共"+money+"元");
      for (int i = 0; i <memberIndex; i++) {
        new Thread(new Member(i, bag,name)).start();
      }
    }
  }
}