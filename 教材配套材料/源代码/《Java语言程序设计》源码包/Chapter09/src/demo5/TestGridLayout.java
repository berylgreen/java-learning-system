package demo5;
import java.awt.*;
import javax.swing.*;
public class TestGridLayout extends JFrame{
	public TestGridLayout()  {
		setTitle("使用网格布局管理器的窗体");
		Container con = getContentPane();//将面板设置为容器
		setLayout(new GridLayout(5,6,5,5));//设置容器使用网格布局管理器
		for (int i = 0; i < 30; i++) {
			con.add(new JButton("按钮"+i));
		}
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TestGridLayout();
	}
}