package demo14;

import java.awt.*;
import javax.swing.*;
public class TestJCheckBox {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		jf.add(new JCheckBox("aa"));			// 创建复选框并添加到JFrame
		jf.add(new JCheckBox("bb"));
		jf.add(new JCheckBox("cc"));
		jf.setLayout(new FlowLayout());
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}