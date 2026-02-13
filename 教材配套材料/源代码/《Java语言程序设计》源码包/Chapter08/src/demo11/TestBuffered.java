package demo11;

import java.io.*;
public class TestBuffered {
	public static void main(String[] args) throws IOException {
		// 创建文件输入流对象
		FileInputStream fis = new FileInputStream("src\\test.jpg");
		// 创建文件输出流对象
		FileOutputStream fos = new FileOutputStream("tar\\test.jpg");
		// 3.将创建的节点流的对象作为形参传递给缓冲流的构造方法中
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int len;// 定义len，记录每次读取的字节
		// 拷贝文件前的系统时间
		long begin = System.currentTimeMillis();
		// 读取文件并判断是否到达文件末尾
		while ((len = bis.read()) != -1) {
			bos.write(len);			// 将读到的字节写入文件
		}
		// 拷贝文件后的系统时间
		long end = System.currentTimeMillis();
		System.out.println("拷贝文件耗时：" + (end - begin) + "毫秒");
		bos.close();
		bis.close();
	}
}