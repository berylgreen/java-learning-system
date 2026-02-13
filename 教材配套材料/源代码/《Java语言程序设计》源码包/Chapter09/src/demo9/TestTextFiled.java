package demo9;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestTextFiled {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		// 创建文本框
		final JTextField jtf = new JTextField("1000phone", 15);
		jf.add(jtf); 							// 将文本框添加到JFrame
		JButton jb = new JButton("清空");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("");				// 清空文本框
				jtf.requestFocus();				// 回到文本框焦点
			}
		});
		jf.add(jb);
		jf.setLayout(new FlowLayout());
		jf.setSize(500, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}