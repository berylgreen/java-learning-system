package com.qianfeng.demo5;

import java.text.SimpleDateFormat;
import java.util.Date;
public class TestSleep {
  public static void main(String[] args) throws Exception {
    for (int i = 0; i < 5; i++) {
      System.out.println("当前时间："
          + new SimpleDateFormat("hh:mm:ss").format(new Date()));
      Thread.sleep(2000);
    }
  }
}