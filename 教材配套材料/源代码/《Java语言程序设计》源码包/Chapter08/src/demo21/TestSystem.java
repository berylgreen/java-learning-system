package demo21;

import java.util.Scanner;
public class TestSystem {
	public static void main(String[] args) {
		// 创建标准输入流
		Scanner s = new Scanner(System.in);
		System.out.println("请输入一个字母：");
		String next = s.next();			// 接收输入的字母
		try {
			Integer.parseInt(next);		// 将字母解析成Integer类型
		} catch (Exception e) {
			System.err.println(e);		// 打印错误信息
			System.out.println("程序内部发生错误");
		}
	}
}