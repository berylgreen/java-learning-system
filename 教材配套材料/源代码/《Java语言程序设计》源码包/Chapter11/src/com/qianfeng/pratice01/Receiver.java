package com.qianfeng.pratice01;

import java.io.*;
import java.net.*;

// 接收端线程
class Receiver implements Runnable {
  public void run() {
    try {
      // 接收端需指定端口
      DatagramSocket ds = new DatagramSocket(9090);
      byte[] buf = new byte[1024];
      DatagramPacket dp = new DatagramPacket(buf, buf.length);
      String line = null;
      // 当收到消息为exit时，退出
      do {
        ds.receive(dp);
        line = new String(buf, 0, dp.getLength());
        System.out.println(line);
      } while (!line.equals("exit"));
      ds.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}