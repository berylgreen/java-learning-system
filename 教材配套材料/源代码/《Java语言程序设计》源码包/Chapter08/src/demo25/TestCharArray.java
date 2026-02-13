package demo25;

import java.io.*;
public class TestCharArray {
	public static void main(String[] args) throws IOException {
		// 创建字符内存输出流
		CharArrayWriter caw = new CharArrayWriter();
		caw.write("a");
		caw.write("b");
		caw.write("c");
		System.out.println(caw);
		caw.close();
		// 将内存中数据转为char[]数组
		char[] charArray = caw.toCharArray();
		System.out.println("***********************");
		// 创建字符内存输入流，读取内存中的char[]数组
		CharArrayReader car = new CharArrayReader(charArray);
		int len;
		while ((len = car.read()) != -1) {
			System.out.println((char) len);
		}
	}
}