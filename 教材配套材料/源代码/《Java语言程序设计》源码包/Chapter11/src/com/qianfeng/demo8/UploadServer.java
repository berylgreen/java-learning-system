package com.qianfeng.demo8;

import com.qianfeng.demo9.TestUploadClient;
import java.io.*;
import java.net.*;
public class UploadServer {
  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(9090);	// 创建服务器端
    System.out.println("服务端已开启，等待接收文件！");
    Socket s = ss.accept();						// 客户端连接服务器端
    System.out.println("正在接收来自" +
        s.getInetAddress().getHostAddress() + "的文件...");
    receiveFile(s);								// 连接成功，开始传输文件
    ss.close();
  }
  private static void receiveFile(Socket socket) throws Exception {
    // buffer起缓冲作用，一次读取或写入多个字节的数据
    byte[] buffer = new byte[1024];
    // 创建DataInputStream对象，可以调用它的readUTF方法来读取要传输的文件名
    DataInputStream dis = new DataInputStream(socket.getInputStream());
    // 首先读取文件名
    String oldFileName = dis.readUTF();
    // 文件路径采用与客户端相同的路径，文件名重新命名，创建好客户端后放开
    String filePath = TestUploadClient.fileDir
        + genereateFileName(oldFileName);
    System.out.println("接收文件成功，另存为：" + filePath);
    // 利用FileOutputStream来操作文件输出流
    FileOutputStream fos = new FileOutputStream(new File(filePath));
    int length = 0;
    while ((length = dis.read(buffer, 0, buffer.length)) > 0) {
      fos.write(buffer, 0, length);
      fos.flush();
    }
    dis.close();								// 释放资源
    fos.close();
    socket.close();
  }
  private static String genereateFileName(String oldName) {
    String newName = null;
    newName = oldName.substring(0, oldName.lastIndexOf(".")) + "-2"
        + oldName.substring(oldName.lastIndexOf("."));
    return newName;
  }
}