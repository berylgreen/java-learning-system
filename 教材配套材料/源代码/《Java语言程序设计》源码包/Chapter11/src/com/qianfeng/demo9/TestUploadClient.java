package com.qianfeng.demo9;

import java.io.*;
import java.net.*;
public class TestUploadClient {
  // 定义要发送的文件路径
  public static final String fileDir = "E:/IdeaProjects/Chapter10/src/";
  public static void main(String[] args) throws Exception {
    String fileName = "test.jpg";		// 要发送的文件名称
    String filePath = fileDir + fileName;
    System.out.println("正在发送文件：" + filePath);
    Socket socket = new Socket(InetAddress.
        getByName("127.0.0.1"), 9090);
    if (socket != null) {
      System.out.println("发送成功!");
      sendFile(socket, filePath);
    }
  }
  private static void sendFile(Socket socket, String filePath)
      throws Exception {
    byte[] bytes = new byte[1024];
    BufferedInputStream bis = new BufferedInputStream(
        new FileInputStream(new File(filePath)));
    DataOutputStream dos = new DataOutputStream(
        new BufferedOutputStream(socket.getOutputStream()));
    // 首先发送文件名 客户端发送使用writeUTF方法，服务器端应该使用readUTF方法
    dos.writeUTF(getFileName(filePath));
    int length = 0;						// 发送文件的内容
    while ((length = bis.read(bytes, 0, bytes.length)) > 0) {
      dos.write(bytes, 0, length);
      dos.flush();
    }
    bis.close();						// 释放资源
    dos.close();
    socket.close();
  }
  private static String getFileName(String filePath) {
    String[] parts = filePath.split("/");
    return parts[parts.length - 1];
  }
}