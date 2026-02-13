package demo24;

import java.io.*;
public class TestByteArray {
	public static void main(String[] args) throws Exception {
		int a = 0;
		int b = 1;
		int c = 2;
		// 创建字节内存输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(a);
		baos.write(b);
		baos.write(c);
		baos.close();
		byte[] buff = baos.toByteArray();	// 转为byte[]数组
		for (int i = 0; i < buff.length; i++)
			System.out.println(buff[i]);	// 遍历数组内容
		System.out.println("***********************");
		// 创建字节内存输入流，读取内存中的byte[]数组
		ByteArrayInputStream bais = new ByteArrayInputStream(buff);
		while ((b = bais.read()) != -1) {
			System.out.println(b);
		}
		bais.close();
	}
}