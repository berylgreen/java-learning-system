package com.qianfeng.demo3;

import java.net.*;
public class TestSend {
  public static void main(String[] args) throws Exception {
    // 创建DatagramSocket对象，指定端口号为8090
    DatagramSocket ds = new DatagramSocket(8090);
    // 要发送的数据
    byte[] by = "1000phone.com".getBytes();
    // 指定接收端IP为127.0.0.1，端口号为8081
    DatagramPacket dp = new DatagramPacket(by, 0, by.length,
        InetAddress.getByName("127.0.0.1"), 8081);
    System.out.println("正在发送数据...");
    ds.send(dp);						// 发送数据
    ds.close();
  }
}