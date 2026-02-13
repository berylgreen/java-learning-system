package com.qianfeng.demo20;

public class TestRegex {

	public static void main(String[] args) {
		String str1 = "www.1000phone.com";
		boolean matches = str1.matches("[a-zA-Z0-9]*");
		System.out.println(matches);
		System.out.println(str1.replaceAll("w", "%"));
		System.out.println(str1.replaceFirst("w", "%"));
		String str2 = "192.168.0.0…";
		String[] split1 = str2.split("\\.");
		for (String s : split1) {
			System.out.print("[" + s + "]");
		}
		System.out.println();
		String[] split2 = str2.split("\\.", -2);
		for (String s : split2) {
			System.out.print("[" + s + "]");
		}
		System.out.println();
		String[] split3 = str2.split("\\.", 3);
		for (String s : split3) {
			System.out.print("[" + s + "]");
		}
		System.out.println();
		String[] split4 = str2.split("\\.", 0);
		for (String s : split4) {
			System.out.print("[" + s + "]");
		}
	}
}