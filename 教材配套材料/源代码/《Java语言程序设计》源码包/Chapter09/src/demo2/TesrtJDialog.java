package demo2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyJDialog extends JDialog {
	public MyJDialog(JFrame frame) {
		super(frame);
		Container container = this.getContentPane();
		JLabel jl = new JLabel("JDialog对话框");
		container.add(jl);
		this.setBounds(150, 150, 100, 100);
	}
}
class SimpleFrame extends JFrame{
	public void createJFrame(String title){
		JFrame jf = new JFrame("JFrame窗口");
		Container container = jf.getContentPane();
		container.setBackground(Color.white);
		JLabel jl = new JLabel(title);
		container.add(jl);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		JButton jb = new JButton("按钮");
		jf.add(jb);
		//为按钮添加点击事件
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建MyJDialog对话框
				MyJDialog dl = new MyJDialog(SimpleFrame.this);
				dl.setVisible(true);
			}
		});
		jf.setLayout(new FlowLayout());
		jf.setSize(500, 300);
		jf.setVisible(true);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
public class TesrtJDialog {
	public static void main(String[] args) {
		SimpleFrame sf = new SimpleFrame();
		sf.createJFrame("JFrame窗口");
	}
}