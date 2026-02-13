package com.qianfeng.demo7;

import com.qianfeng.demo6.TestThread;
import java.io.*;
import java.net.*;
public class TestServerThread {
  public static void main(String[] args) throws IOException {
    ServerSocket ss = null;
    Socket s = null;
    ss = new ServerSocket(9090);
    boolean flag = true;
    while (flag) {
      System.out.println("等待接收数据...");
      s = ss.accept();
      new Thread(new TestThread(s)).start();
    }
    ss.close();
    InputStream is = s.getInputStream();
    byte[] b = new byte[20];
    int len;
    while ((len = is.read(b)) != -1) {
      String str = new String(b, 0, len);
      System.out.print(str);
    }
    OutputStream os = s.getOutputStream();
    os.write("服务端已收到".getBytes());
    os.close();
    is.close();
    s.close();
    ss.close();
  }
}