package demo16;

import java.io.*;
public class TestConvert {
	public static void main(String[] args) throws Exception {
		// 创建字节输入流
		FileInputStream fis = new FileInputStream("src/source.txt");
		// 将字节输入流转换为字符输入流
		InputStreamReader isr = new InputStreamReader(fis);
		// 创建字节输出流
		FileOutputStream fos = new FileOutputStream("src/target.txt");
		// 将字节输出流转换成字符输出流
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		int str;
		while ((str = isr.read()) != -1) {
			osw.write(str);
		}
		osw.close();
		isr.close();
	}
}