package demo4;
import java.awt.*;
import javax.swing.*;
public class TestBorderLayout extends JFrame {
	public TestBorderLayout() {
		setTitle("使用边界布局管理器的窗体");
		Container c = getContentPane(); // 定义一个容器
		setLayout(new BorderLayout()); // 设置容器为边界布局管理器
		JButton button1 = new JButton("中"),
			button2 = new JButton("北"),
			button3 = new JButton("南"),
			button4 = new JButton("西"),
			button5 = new JButton("东");
		c.add(button1, BorderLayout.CENTER);// 中部添加按钮
		c.add(button2, BorderLayout.NORTH);// 北部添加按钮
		c.add(button3, BorderLayout.SOUTH);// 南部添加按钮
		c.add(button4, BorderLayout.WEST);// 西部添加按钮
		c.add(button5, BorderLayout.EAST);// 东部添加按钮
		setSize(500, 300); // 设置窗体大小
		setVisible(true); // 设置窗体可见
		// 设置窗体关闭方式
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TestBorderLayout();
	}
}