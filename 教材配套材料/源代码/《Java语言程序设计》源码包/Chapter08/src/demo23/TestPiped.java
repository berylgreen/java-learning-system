package demo23;

import java.io.*;
public class TestPiped {
	public static void main(String[] args) throws IOException {
		Send send = new Send();
		Receive recive = new Receive();
		// 写入
		PipedOutputStream pos = send.getOutputStream();
		// 读出
		PipedInputStream pis = recive.getInputStream();
		pos.connect(pis);				// 将输出发送到输入
		send.start();					// 启动线程
		recive.start();
	}
}
class Send extends Thread {
	private PipedOutputStream pos = new PipedOutputStream();
	public PipedOutputStream getOutputStream() {
		return pos;
	}
	public void run() {
		String s = new String("Send发送的数据");
		try {
			pos.write(s.getBytes());	// 写入数据
			pos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class Receive extends Thread {
	private PipedInputStream pis = new PipedInputStream();
	public PipedInputStream getInputStream() {
		return pis;
	}
	public void run() {
		String s = null;
		byte[] b = new byte[1024];
		try {
			int len = pis.read(b);
			s = new String(b, 0, len);
			// 读出数据
			System.out.println("Receive接收到了：" + s);
			pis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}