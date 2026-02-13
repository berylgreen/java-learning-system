package com.qianfeng.demo6;

import java.io.*;
import java.net.Socket;
public class TestThread implements Runnable {
  private Socket client = null; 				// 接收客户端
  public TestThread(Socket client) {
    this.client = client; 					// 通过构造方法设置Socket
  }
  public void run() {
    BufferedReader br = null; 				// 用于接收客户端信息
    PrintStream ps = null; 					// 定义输出流
    try {
      br = new BufferedReader(new InputStreamReader(
          client.getInputStream())); 	// 获得客户端信息
      // 实例化客户端输出流
      ps = new PrintStream(client.getOutputStream());
      boolean flag = true; 				// 标记客户端是否操作完毕
      while (flag) {
        String str = br.readLine();
        if (str == null || "".equals(str)) {
          flag = false; 				// 输入信息为空客户端操作结束
        } else {
          System.out.println(str);
          if ("bye".equals(str)) {
            flag = false; 			// 输入信息为bye客户端操作接收
          } else {
            // 响应客户端的信息
            ps.println("服务端已收到");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally { 							// 释放资源
      if (ps != null) {
        ps.close();
      }
      if (client != null) {
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}