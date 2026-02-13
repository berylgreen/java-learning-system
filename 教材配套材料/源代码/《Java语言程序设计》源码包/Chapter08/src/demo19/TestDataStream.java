package demo19;

import java.io.*;
public class TestDataStream {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("src/data.txt");
		DataOutputStream dos = new DataOutputStream(fos);
		dos.write(10);							// 写入数据，默认字节形式
		dos.writeChar('c');						// 写入一个字符
		dos.writeBoolean(true);					// 写入一个布尔类型的值
		dos.writeUTF("千锋教育");				// 写入以UTF-8编码的字符串
		dos.close();
		FileInputStream fis = new FileInputStream("src/data.txt");
		DataInputStream dis = new DataInputStream(fis);
		System.out.println(dis.read());			// 读取一个字节
		System.out.println(dis.readChar());	// 读取一个字符
		System.out.println(dis.readBoolean());	// 读取一个布尔值
		System.out.println(dis.readUTF());		// 读取UTF-8编码的字符串
		dis.close();
	}
}