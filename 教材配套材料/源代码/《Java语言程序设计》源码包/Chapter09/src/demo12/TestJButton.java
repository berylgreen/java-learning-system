package demo12;

import java.awt.*;
import javax.swing.*;
public class TestJButton {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		Icon icon = new ImageIcon("src/button.png");
		JButton jb = new JButton(icon);			// 创建按钮
		jf.add(jb); 							// 添加按钮
		jf.setLayout(new FlowLayout()); 		// 设置布局
		jf.setSize(300, 250);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}