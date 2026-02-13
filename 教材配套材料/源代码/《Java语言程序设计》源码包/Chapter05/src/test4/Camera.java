package test4;

import test1.USB;

public class Camera implements USB {

  public void turnOn() {
    System.out.println("摄像头启动了...");
  }

  public void turnOff() {
    System.out.println("摄像头关闭了...");
  }
}