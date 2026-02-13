package test5;

import test1.USB;

public class Computer {

  //计算机的USB插槽
  private USB[] usbArr = new USB[4];//向算机上连接一个USB设备

  public void add(USB usb) {
    //查看所有插槽
    for (int i = 0; i < usbArr.length; i++) {
      //如果发现一个空的
      if (usbArr[i] == null) {
        //将usb设备连接在这个插槽上
        usbArr[i] = usb;//连接上之后结束循环
        break;
      }
    }
  }

  //开机功能
  public void powerOn() {
    //查看所有插槽
    for (int i = 0; i < usbArr.length; i++) {
      //如果发现有设备
      if (usbArr[i] != null) {
        //将USB设备启动
        usbArr[i].turnOn();
        System.out.println("计算机开机成功!");
      }
    }
  }

  //关机功能
  public void powerOff() {
    for (int i = 0; i < usbArr.length; i++) {
      if (usbArr[i] != null) {
        usbArr[i].turnOff();
      }
      System.out.println("计算机关机成功!");
    }
  }
}  