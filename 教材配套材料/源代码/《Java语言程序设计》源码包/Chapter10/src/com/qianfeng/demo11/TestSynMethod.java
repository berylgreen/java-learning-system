package com.qianfeng.demo11;

public class TestSynMethod {
  public static void main(String[] args) {
    Ticket3 ticket = new Ticket3();
    Thread t1 = new Thread(ticket, "窗口一");
    Thread t2 = new Thread(ticket, "窗口二");
    Thread t3 = new Thread(ticket, "窗口三");
    t1.start();
    t2.start();
    t3.start();
  }
}
class Ticket3 implements Runnable {
  private int ticket = 5;
  public synchronized void run() {
    for (int i = 0; i < 100; i++) {
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