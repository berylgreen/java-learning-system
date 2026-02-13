package com.qianfeng.demo10;

import java.net.*;
import javax.swing.JTextArea;
public class TestReceive extends Thread {
  private String sendIP = "127.0.0.1";
  private int sendPORT = 9090;
  private int receivePORT = 9095;
  // 声明发送信息的数据报套结字
  private DatagramSocket sendSocket = null;
  // 声明发送信息的数据包
  private DatagramPacket sendPacket = null;
  // 声明接受信息的数据报套结字
  private DatagramSocket receiveSocket = null;
  // 声明接受信息的数据报
  private DatagramPacket receivePacket = null;
  // 缓冲数组的大小
  public static final int BUFFER_SIZE = 5120;
  private byte inBuf[] = null; 				// 接收数据的缓冲数组
  JTextArea jta;
  public TestReceive(JTextArea jta) {		// 构造方法
    this.jta = jta;
  }
  public void run() {
    try {
      inBuf = new byte[BUFFER_SIZE];
      receivePacket = new DatagramPacket(inBuf, inBuf.length);
      receiveSocket = new DatagramSocket(receivePORT);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (true) {
      if (receiveSocket == null) {
        break;
      } else {
        try {
          receiveSocket.receive(receivePacket);
          String message = new String(receivePacket.getData(), 0,
              receivePacket.getLength());
          jta.append("收到窗口2信息：" + message + "\n");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  public void sendData(byte buffer[]) {		// 发送数据
    try {
      InetAddress address = InetAddress.getByName(sendIP);
      sendPacket = new DatagramPacket(buffer, buffer.length, address,
          sendPORT);
      sendSocket = new DatagramSocket();
      sendSocket.send(sendPacket);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void closeSocket() {					// 释放资源
    receiveSocket.close();
  }
}