package com.qianfeng.demo25;

import java.util.Random;

public class TestRandom {

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println("-----3 个 int 类型随机数-----");
		for (int i = 0; i < 3; i++) {
			System.out.println(r.nextInt());
		}
		System.out.println("-----3 个 0.0～100.0 的 double 类型随机数-----");
		for (int i = 0; i < 3; i++) {
			System.out.println(r.nextDouble() * 100);
		}
		Random r2 = new Random(10);
		System.out.println("-----3 个 int 类型随机数-----");
		for (int i = 0; i < 3; i++) {
			System.out.println(r2.nextInt());
		}
		System.out.println("-----3 个 0.0～100.0 的 double 类型随机数-----");
		for (int i = 0; i < 3; i++) {
			System.out.println(r2.nextDouble() * 100);
		}
	}
}