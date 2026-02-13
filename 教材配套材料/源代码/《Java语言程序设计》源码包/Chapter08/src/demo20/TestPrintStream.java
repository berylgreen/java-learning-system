package demo20;

import java.io.*;
public class TestPrintStream {
	public static void main(String[] args) throws Exception {
		// 创建PrintStream对象，将FileOutputStream读取到的数据输出
		PrintStream ps = new PrintStream
			(new FileOutputStream("src/print.txt"),true);
		ps.print(2022);
		ps.println("年");
		ps.print("疫情退散！");
	}
}