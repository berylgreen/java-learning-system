package demo12;

import java.io.*;
public class TestFileReader {
	public static void main(String[] args) throws Exception {
		File file = new File("read.txt");
		FileReader fr = new FileReader(file);
		int len; 			// 定义len，记录读取的字符
		// 判断是否读取到文件的末尾
		while ((len = fr.read()) != -1) {
			// 打印文件内容
			System.out.print((char) len);
		}
		fr.close(); 		// 释放资源
	}
}