package com.qianfeng.demo1;

import java.net.InetAddress;
public class TestInetAddress {
  public static void main(String[] args) throws Exception {
    // 返回本地IP地址对应的InetAddress实例
    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println("本机的IP地址：" + localHost.getHostAddress());
    // 根据主机名返回对应的InetAddress实例
    InetAddress ip = InetAddress.getByName("www.mobiletrain.org");
    System.out.println("2秒内是否可达：" + ip.isReachable(2000));
    System.out.println("1000phone的IP地址：" + ip.getHostAddress());
    System.out.println("1000phone的主机名：" + ip.getHostName());
  }
}