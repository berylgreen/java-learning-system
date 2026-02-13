package demo11;

import java.awt.*;
import javax.swing.*;
public class TestJTextArea {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		JTextArea jta = new JTextArea("自动换行的文本域", 6, 6);
		jta.setSize(190, 200);
		jta.setLineWrap(true);
		jf.add(jta);
		jf.setLayout(new FlowLayout()); 		// 设置布局
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}