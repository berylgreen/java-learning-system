package test6;

import test2.Mouse;
import test3.Keyboard;
import test4.Camera;
import test5.Computer;

public class TestUSB {

	public static void main(String[] args) {
		//实例化计算机类对象
		Computer c = new Computer();
		//向计算机中添加鼠标、键盘和摄像头设备
		c.add(new Mouse());
		c.add(new Keyboard());
		c.add(new Camera());
		c.powerOn(); //启动计算机
		System.out.println("****计算机运行****");
		c.powerOff(); //关闭计算机
	}
}