package demo18;

import javax.swing.*;
import javax.swing.tree.*;
public class TestJTree {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 	// 创建JFrame窗体
		// 创建树中所有节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("中国");
		DefaultMutableTreeNode bj = new DefaultMutableTreeNode("北京");
		DefaultMutableTreeNode hb = new DefaultMutableTreeNode("河北");
		DefaultMutableTreeNode lf = new DefaultMutableTreeNode("廊坊");
		DefaultMutableTreeNode sjz = new DefaultMutableTreeNode("石家庄");
		// 建立节点之间的父子关系
		hb.add(lf);
		hb.add(sjz);
		root.add(bj);
		root.add(hb);
		JTree tree = new JTree(root);			// 创建树
		jf.add(new JScrollPane(tree));
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}