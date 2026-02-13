package com.qianfeng.demo2;

import java.net.*;
public class TestReceive {
  public static void main(String[] args) throws Exception {
    // 创建DatagramSocket对象，指定端口号为8081
    DatagramSocket ds = new DatagramSocket(8081);
    byte[] by = new byte[1024];		// 创建接收数据的数组
    // 创建DatagramPacket对象，用于接收数据
    DatagramPacket dp = new DatagramPacket(by, by.length);
    System.out.println("等待接收数据...");
    ds.receive(dp);					// 等待接收数据，没有数据会阻塞
    // 获得接收数据的内容和长度
    String str = new String(dp.getData(), 0, dp.getLength());
    // 打印接收到的信息
    System.out.println(str + "-->" + dp.getAddress().
        getHostAddress() + ":"+ dp.getPort());
    ds.close();
  }
}