package com.qianfeng.demo22;

public class TestSystemDemo02 {

	public static void main(String[] args) {
		System.out.println("当前系统版本为：" + System.getProperty("os.name")
			+ System.getProperty("os.version")
			+ System.getProperty("os.arch"));
		System.out.println("当前系统用户名为：" +
			System.getProperty("user.name"));
		System.out.println("当前用户工作目录：" +
			System.getProperty("user.dir"));
	}
}