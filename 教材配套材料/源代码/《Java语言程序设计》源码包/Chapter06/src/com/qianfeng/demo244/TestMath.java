package com.qianfeng.demo244;

public class TestMath {

	public static void main(String[] args) {
		System.out.println("-10 的绝对值是：" + Math.abs(-10));
		System.out.println("大于 2.5 的最小整数是：" + Math.ceil(2.5));
		System.out.println("小于 2.5 的最大整数是：" + Math.floor(2.5));
		System.out.println("5 和 6 的较大值是：" + Math.max(5, 6));
		System.out.println("5 和 6 的较小值是：" + Math.min(5, 6));
		System.out.println("6.6 四舍五入后是：" + Math.round(6.6));
		System.out.println("36 的平方根是：" + Math.sqrt(36));
		System.out.println("2 的 3 次幂是：" + Math.pow(2, 3));
		for (int i = 0; i < 5; i++) {
			System.out.println("随机数" + (i + 1) + "->" + Math.random());
		}
	}
}