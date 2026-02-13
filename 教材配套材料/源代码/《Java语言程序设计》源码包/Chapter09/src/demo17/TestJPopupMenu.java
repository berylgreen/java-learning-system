package demo17;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestJPopupMenu {
	public static void main(String[] args) {
		final JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		final JPopupMenu jpm = new JPopupMenu();		// 创建菜单
		// 创建两个菜单项
		JMenuItem item1 = new JMenuItem("保存");
		JMenuItem item2 = new JMenuItem("退出");
		// 为第二个菜单项添加事件监听
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jpm.add(item1);									// 将菜单项添加到菜单
		jpm.add(item2);
		// 为JFrame添加鼠标点击事件监听器
		jf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3) {
					jpm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		jf.setLayout(new FlowLayout()); 				// 设置布局
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}