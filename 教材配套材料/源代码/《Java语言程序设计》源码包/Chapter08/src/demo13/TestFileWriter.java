package demo13;

import java.io.*;
public class TestFileWriter {
	public static void main(String[] args) throws IOException {
		File file = new File("read.txt");
		FileWriter fw = new FileWriter(file);
		fw.write(".com"); 		// 写入文件的内容
		System.out.println("已保存到read.txt!");
		fw.close(); 			// 释放资源
	}
}