package demo27;

import java.io.*;
import java.util.*;
public class TestSequence2 {
	public static void main(String[] args) throws Exception {
		// 创建两个文件输入流读取2个文件
		FileInputStream fis1 = new FileInputStream("src/file1.txt");
		FileInputStream fis2 = new FileInputStream("src/file2.txt");
		FileInputStream fis3 = new FileInputStream("src/file3.txt");
		// 创建Vector对象
		Vector vector = new Vector();
		vector.addElement(fis1);
		vector.addElement(fis2);
		vector.addElement(fis3);
		// 获取Vector对象中的元素
		Enumeration elements = vector.elements();
		// 将Enumeration对象中的流合并
		SequenceInputStream sis = new SequenceInputStream(elements);
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