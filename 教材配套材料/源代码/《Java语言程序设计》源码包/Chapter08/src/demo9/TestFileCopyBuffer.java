package demo9;

import java.io.*;
public class TestFileCopyBuffer {
	public static void main(String[] args) throws Exception {
		// 创建文件输入流对象
		FileInputStream fis = new FileInputStream("src/test.jpg");
		// 创建文件输出流对象
		FileOutputStream fos = new FileOutputStream("tar/test.jpg");
		byte[] b = new byte[512]; 	// 定义缓冲区大小
		int len; 					// 定义len，记录每次读取的字节
		// 拷贝文件前的系统时间
		long begin = System.currentTimeMillis();
		// 读取文件并判断是否到达文件末尾
		while ((len = fis.read(b)) != -1) {
			fos.write(b, 0, len); 	// 从第1个字节开始，向文件写入len个字节
		}
		// 拷贝文件后的系统时间
		long end = System.currentTimeMillis();
		System.out.println("拷贝文件耗时；" + (end - begin) + "毫秒");
		fos.close(); 				// 释放资源
		fis.close();
	}
}