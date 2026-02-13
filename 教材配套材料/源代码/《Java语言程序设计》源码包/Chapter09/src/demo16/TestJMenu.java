package demo16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestJMenu {

	public static void main(String[] args) {
		final JFrame jf = new JFrame("JFrame窗口");    // 创建JFrame窗体
		JMenuBar jmb = new JMenuBar();                    // 创建菜单栏
		jf.setJMenuBar(jmb);
		JMenu jm = new JMenu("文件");                    // 创建菜单
		jmb.add(jm);
		// 创建两个菜单项
		JMenuItem item1 = new JMenuItem("保存");
		JMenuItem item2 = new JMenuItem("退出");
		// 为第二个菜单项添加事件监听
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jm.add(item1);                                    // 将菜单项添加到菜单
		jm.addSeparator();                                // 添加分隔符
		jm.add(item2);
		jf.setLayout(new FlowLayout());                // 设置布局
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}