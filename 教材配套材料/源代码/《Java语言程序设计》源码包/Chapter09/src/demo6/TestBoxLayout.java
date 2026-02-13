package demo6;
import java.awt.*;
import javax.swing.*;
public class TestBoxLayout extends JFrame{
	public TestBoxLayout(){
		setTitle("使用盒子布局管理器的窗体");
		JPanel boxPanel=new JPanel();
		Container con = getContentPane();
		con.add(boxPanel);
		boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
		for (int i = 0; i < 3; i++) {
			boxPanel.add(new JButton("按钮"+i));
		}
		setSize(300,200);   //设置容器的大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TestBoxLayout();
	}
}