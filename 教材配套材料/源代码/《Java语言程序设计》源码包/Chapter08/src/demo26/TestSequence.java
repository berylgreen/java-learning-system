package demo26;

import java.io.*;
public class TestSequence {
	public static void main(String[] args) throws Exception {
		// 创建两个文件输入流读取2个文件
		FileInputStream fis1 = new FileInputStream("src/file1.txt");
		FileInputStream fis2 = new FileInputStream("src/file2.txt");
		// SequenceInputStream对象用于合并两个文件输入流
		SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
		FileOutputStream fos = new FileOutputStream("src/fileMerge.txt");
		int len;
		byte[] buff = new byte[1024];
		while ((len = sis.read(buff)) != -1) {
			fos.write(buff, 0, len);
			fos.write("\r\n".getBytes());
		}
		sis.close();
		fos.close();
	}
}