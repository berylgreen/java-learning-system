package com.qianfeng.pratice01;

import java.io.BufferedReader;
import java.io.*;
import java.net.*;

class Sender implements Runnable {
  public void run() {
    try {
      // 建立Socket，无需指定端口
      DatagramSocket ds = new DatagramSocket();
      // 通过控制台标准输入
      BufferedReader br = new BufferedReader(new InputStreamReader(
          System.in));
      String line = null;
      DatagramPacket dp = null;
      // do-while结构，发送为exit时，退出
      do {
        line = br.readLine();
        byte[] buf = line.getBytes();
        // 指定为广播ip
        dp = new DatagramPacket(buf, buf.length,
            InetAddress.getByName("127.0.0.1"), 9090);
        ds.send(dp);
      } while (!line.equals("exit"));
      ds.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}