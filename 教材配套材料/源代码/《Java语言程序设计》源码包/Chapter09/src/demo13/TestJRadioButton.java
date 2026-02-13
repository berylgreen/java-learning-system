package demo13;

import java.awt.*;
import javax.swing.*;
public class TestJRadioButton {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		JRadioButton jrb1 = new JRadioButton("aa");
		JRadioButton jrb2 = new JRadioButton("bb");
		JRadioButton jrb3 = new JRadioButton("cc");
		ButtonGroup bg = new ButtonGroup();	// 创建按钮组
		bg.add(jrb1);							// 添加到按钮组
		bg.add(jrb2);
		bg.add(jrb3);
		jf.add(jrb1);							// 添加到JFrame
		jf.add(jrb2);
		jf.add(jrb3);
		jf.setLayout(new FlowLayout()); 		// 设置布局
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}