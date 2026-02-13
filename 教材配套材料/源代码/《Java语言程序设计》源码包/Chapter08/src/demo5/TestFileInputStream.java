package demo5;

import java.io.*;
public class TestFileInputStream {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			// 创建文件输入流对象
			fis = new FileInputStream("D://read.txt");
			int n = 512;			 // 设定读取的字节数
			byte buffer[] = new byte[n];
			// 读取输入流
			while ((fis.read(buffer, 0, n) != -1) && (n > 0)) {
				System.out.print(new String(buffer));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				fis.close();		// 释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}