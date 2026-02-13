package com.qianfeng.demo4;

import java.io.*;
import java.net.*;
public class TestServer {
  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(9090);
    System.out.println("等待接收数据...");
    Socket s = ss.accept();
    InputStream is = s.getInputStream();
    byte[] b = new byte[20];
    int len;
    while ((len = is.read(b)) != -1) {
      String str = new String(b, 0, len);
      System.out.print(str);
    }
    OutputStream os = s.getOutputStream();
    os.write("服务端已收到 This is Server".getBytes());
    os.close();
    is.close();
    s.close();
    ss.close();
  }
}