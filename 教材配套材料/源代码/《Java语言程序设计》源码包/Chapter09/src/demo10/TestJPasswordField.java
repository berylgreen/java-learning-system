package demo10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestJPasswordField {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		// 创建密码框
		final JPasswordField jpf = new JPasswordField("1000phone", 15);
		jpf.setEchoChar('$');
		jf.add(jpf); // 将文本框添加到JFrame
		JButton jb = new JButton("清空");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpf.setText(""); 				// 清空文本框
				jpf.requestFocus(); 			// 回到文本框焦点
			}
		});
		jf.add(jb);
		jf.setLayout(new FlowLayout());
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}