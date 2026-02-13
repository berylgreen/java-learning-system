package demo19;

import javax.swing.*;

public class TestJTable {

	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口");        // 创建JFrame窗体
		String[] title = {"序号", "教室", "课程"};    // 定义表格标题
		// 定义表格数据
		Object[][] data = {new Object[]{1, 12, "Java"},
			new Object[]{2, 9, "IOS"},
			new Object[]{3, 15, "Android"}};
		JTable table = new JTable(data, title);    // 创建JTable
		jf.add(new JScrollPane(table));
		jf.setSize(200, 150);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}