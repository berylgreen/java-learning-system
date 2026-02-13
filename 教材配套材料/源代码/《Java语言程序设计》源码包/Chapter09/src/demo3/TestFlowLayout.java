package demo3;
import java.awt.*;
import javax.swing.*;
public class TestFlowLayout {
	public static void main(String[] agrs) {
		JFrame jf=new JFrame("使用流布局管理器的窗体");
		JButton btn1=new JButton("1");    //创建按钮
		JButton btn2=new JButton("2");
		JButton btn3=new JButton("3");
		JButton btn4=new JButton("4");
		JButton btn5=new JButton("5");
		JButton btn6=new JButton("6");
		jf.add(btn1);    //添加按钮
		jf.add(btn2);
		jf.add(btn3);
		jf.add(btn4);
		jf.add(btn5);
		jf.add(btn6);
		//向jf添加FlowLayout布局管理器，将组件间的横向和纵向间隙设置为20像素
		jf.setLayout(new FlowLayout(FlowLayout.LEADING,40,40));
		jf.setBackground(Color.gray);
		jf.setBounds(200,200,400,400);    //设置容器的大小
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}