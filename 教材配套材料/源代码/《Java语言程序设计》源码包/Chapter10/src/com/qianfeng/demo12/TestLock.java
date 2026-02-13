package com.qianfeng.demo12;

import com.qianfeng.demo10.Ticket2;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
  public static void main(String[] args) {
    Ticket2 ticket = new Ticket2();
    Thread  t1     = new Thread(ticket, "窗口一");
    Thread  t2     = new Thread(ticket, "窗口二");
    Thread  t3     = new Thread(ticket, "窗口三");
    t1.start();
    t2.start();
    t3.start();
  }
}
class Ticket4 implements Runnable {
  private       int  ticket = 5;
  private final Lock lock   = new ReentrantLock();

  public void run() {
    //进入方法立刻加锁
    lock.lock();//获取锁
    for (int i = 0; i < 100; i++) {
      try {
        if (ticket > 0) {
          System.out.println(
              Thread.currentThread().getName() + "卖出第" + ticket + "张票，还剩" + --ticket + "张票");
        }
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();//释放锁
      }
    }
  }
}