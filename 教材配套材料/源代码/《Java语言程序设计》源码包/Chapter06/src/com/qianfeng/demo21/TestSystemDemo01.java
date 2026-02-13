package com.qianfeng.demo21;

public class TestSystemDemo01 {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		Thread.sleep(100);
		long end = System.currentTimeMillis();
		System.out.println("程序睡眠了" + (end - start) + "毫秒");
	}
}