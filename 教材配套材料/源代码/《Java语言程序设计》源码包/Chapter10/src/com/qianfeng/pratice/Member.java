package com.qianfeng.pratice;

public class Member implements Runnable {
  String[] name;//群成员昵称数组
  int index; //数组的索引
  RedBag bag;//总红包对象
  double idmoney;//成员红包金额
  public  Member(int id,RedBag bag ,String[] name){
    this.index =id;
    this.bag=bag;
    this.name=name;
  }
  public void run() {
    System.out.println(name[index]+"，开始抢红包");
    try{
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    idmoney=bag.shareBag();
    if(idmoney>0){
      System.out.println("恭喜，"+name[index]+"抢到"+idmoney+"元");
    }else {
      System.out.println("很遗憾，"+name[index]+"手速慢了，未抢到红包");
    }
  }
}