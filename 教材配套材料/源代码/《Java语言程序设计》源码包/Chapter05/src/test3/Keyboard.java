package test3;

import test1.USB;

public class Keyboard implements USB {

  public void turnOn() {
    System.out.println("键盘启动了...");
  }

  public void turnOff() {
    System.out.println("键盘关闭了...");
  }
}