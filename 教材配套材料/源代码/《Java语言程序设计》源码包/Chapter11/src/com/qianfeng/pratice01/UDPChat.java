package com.qianfeng.pratice01;

public class UDPChat {
  public static void main(String[] args) {
    // 运行接收端和发送端线程，开始通话
    new Thread(new Sender()).start();
    new Thread(new Receiver()).start();
  }
}