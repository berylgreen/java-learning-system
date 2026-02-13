package demo21;

import java.awt.*;
import java.awt.event.*;
public class TestWindowEvent {
	public static void main(String[] args) {
		// 创建Frame对象
		Frame f = new Frame("Frame窗口");
		f.setSize(300, 200); 		// 设置长和宽
		f.setLocation(500, 200); 	// 设置窗口相对位置
		f.setVisible(true); 		// 设置为可见
		// 创建匿名内部类，监听窗体事件
		f.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				System.out.println("windowOpened-->窗口被打开");
			}
			public void windowIconified(WindowEvent e) {
				System.out.println("windowIconified-->窗口最小化");
			}
			public void windowDeiconified(WindowEvent e) {
				System.out.println("windowDeiconified-->窗口从最小化恢复");
			}
			public void windowDeactivated(WindowEvent e) {
				System.out.println("windowDeactivated-->取消窗口选中");
			}
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing-->窗口正在关闭");
				((Window) e.getComponent()).dispose();
			}
			public void windowClosed(WindowEvent e) {
				System.out.println("windowClosed-->窗口关闭");
			}
			public void windowActivated(WindowEvent e) {
				System.out.println("windowActivated-->窗口被选中");
			}
		});
	}
}