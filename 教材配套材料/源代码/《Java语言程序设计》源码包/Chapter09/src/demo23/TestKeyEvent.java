package demo23;

import java.awt.*;
import java.awt.event.*;
public class TestKeyEvent {
	public static void main(String[] args) {
		// 创建Frame对象
		Frame f = new Frame("Frame窗口");
		Panel p = new Panel();
		TextField tf = new TextField(10); 	// 创建文本框
		p.add(tf);
		f.add(p);
		f.setSize(300, 200); 				// 设置长和宽
		f.setLocation(500, 200); 			// 设置窗口相对位置
		f.setVisible(true); 				// 设置为可见
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed-->键盘"
					+ KeyEvent.getKeyText(e.getKeyCode()) + "键按下");
			}
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased-->键盘"
					+ KeyEvent.getKeyText(e.getKeyCode()) + "键松开");
			}
			public void keyTyped(KeyEvent e) {
				System.out.println("keyTyped-->键盘输入的内容是："
					+ e.getKeyChar());
			}
		});
	}
}