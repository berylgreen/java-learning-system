package demo22;

import java.io.*;
public class TestSystemRedirect {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/src.txt"));	// 重定向输入流
		System.setOut(new PrintStream("src/tar.txt"));		// 重定向输出流
		BufferedReader br = new
			BufferedReader(new InputStreamReader(System.in));
		String str;
		// 判断是否读取到文件末尾
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
	}
}