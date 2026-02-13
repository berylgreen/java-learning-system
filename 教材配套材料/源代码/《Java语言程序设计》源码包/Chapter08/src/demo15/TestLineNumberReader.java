package demo15;

import java.io.*;
public class TestLineNumberReader {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("src/code1.txt");
		FileWriter fw = new FileWriter("src/code2.txt");
		LineNumberReader lnr = new LineNumberReader(fr);
		lnr.setLineNumber(0); 	// 设置文件起始行号
		String str = null;
		while ((str = lnr.readLine()) != null) {
			// 将行号写入文件
			fw.write(lnr.getLineNumber() + ":" + str);
			fw.write("\r\n"); 	// 写入换行
		}
		fw.close();				// 释放资源
		lnr.close();
	}
}