package demo14;

import java.io.*;
public class TestCopyBuffered {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("src/src.txt");
		FileWriter fw = new FileWriter("src/tar.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(fw);
		String str;
		// 每次读取一行文本，判断是否到文件末尾
		while ((str = br.readLine()) != null) {
			bw.write(str);
			// 写入一个换行符，该方法会根据不同操作系统生成相应换行符
			bw.newLine();
		}
		bw.close(); 	// 释放资源
		br.close();
	}
}