package test2;

import test1.USB;

public class Mouse implements USB {
  public void turnOn() {
    System.out.println("鼠标启动了...");
  }
  public void turnOff() {
    System.out.println("鼠标关闭了...");
  }
}