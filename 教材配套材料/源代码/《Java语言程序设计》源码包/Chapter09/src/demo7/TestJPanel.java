package demo7;
import java.awt.*;
import javax.swing.*;
public class TestJPanel {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口"); 		// 创建JFrame窗体
		jf.setLayout(new GridLayout(2, 2, 10, 10));// 设置布局
		JPanel jp1 = new JPanel(new GridLayout(1, 3, 10, 10));
		JPanel jp2 = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel jp3 = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel jp4 = new JPanel(new GridLayout(2, 1, 10, 10));
		jp1.add(new JButton("JPanel的按钮1"));  // 添加JPanel的按钮
		jp2.add(new JButton("JPanel的按钮2"));
		jp3.add(new JButton("JPanel的按钮3"));
		jp4.add(new JButton("JPanel的按钮4"));
		jf.add(jp1);								// 将JPanel添加进JFrame
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		jf.setSize(500, 400);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}