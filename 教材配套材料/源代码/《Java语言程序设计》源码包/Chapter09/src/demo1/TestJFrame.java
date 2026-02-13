package demo1;

import java.awt.*;
import javax.swing.*;

class SimpleFrame extends JFrame {
	public void createJFrame(String title){
		JFrame jf = new JFrame("JFrame窗口");	    // 创建JFrame窗体
		Container container = jf.getContentPane(); // 获取一个容器
		container.setBackground(Color.white);       // 设置窗体的背景色
		JLabel jl = new JLabel(title);               // 创建一个JLable标签
		container.add(jl);                             // 添加标签
		jl.setHorizontalAlignment(SwingConstants.CENTER);// 设置标签居中
		JButton jb = new JButton("按钮");           // 创建一个按钮
		jf.add(jb);								  // 添加按钮
		jf.setLayout(new FlowLayout());			  // 设置布局
		jf.setSize(500, 300);               		  // 设置窗体大小
		jf.setVisible(true);                          // 设置窗体可见
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
public class TestJFrame {
	public static void main(String[] args) {
		new SimpleFrame().createJFrame("这是一个JFrame窗体");
	}
}