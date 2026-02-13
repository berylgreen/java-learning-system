package demo15;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestJComboBox implements ItemListener {
	JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
	JComboBox jcb;
	JPanel p = new JPanel();
	public TestJComboBox() {
		jcb = new JComboBox();				// 创建下拉框
		jcb.addItem("aa");					// 添加下拉框选项
		jcb.addItem("bb");
		jcb.addItem("cc");
		jcb.addItemListener(this);
		p.add(jcb);
		jf.getContentPane().add(p);
		jf.setLayout(new FlowLayout()); 	// 设置布局
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	// 实现事件监听
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String s = (String) jcb.getSelectedItem();
			System.out.println(s);
		}
	}
	public static void main(String args[]) {
		new TestJComboBox();				// 主方法
	}
}