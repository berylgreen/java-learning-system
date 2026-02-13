package com.qianfeng.demo10;

public class TestSynBlock {
  public static void main(String[] args) {
    Ticket2 ticket = new Ticket2();
    Thread t1 = new Thread(ticket, "窗口一");
    Thread t2 = new Thread(ticket, "窗口二");
    Thread t3 = new Thread(ticket, "窗口三");
    t1.start();
    t2.start();
    t3.start();
  }
}
public class Ticket2 implements Runnable {
  private int ticket = 5;
  public void run() {
    for (int i = 0; i < 100; i++) {
      synchronized (this) {
        if (ticket > 0) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(
              Thread.currentThread().getName() + "卖出第" + ticket + "张票，还剩" + --ticket + "张票");
        }
      }
    }
  }
}