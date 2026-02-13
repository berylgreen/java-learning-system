package demo6;

import java.io.*;

public class TestFileOutputStream {
	public static void main(String[] args) throws IOException {
		System.out.print("输入要保存文件的内容：");
		int count, n = 512;
		byte buffer[] = new byte[n];
		count = System.in.read(buffer); 	// 读取标准输入流
		// 创建文件输出流对象
		FileOutputStream fos = new FileOutputStream("D://read.txt");
		fos.write(buffer, 0, count); 		// 写入输出流
		System.out.println("已保存到read.txt!");
		fos.close(); 						// 释放资源
	}
}