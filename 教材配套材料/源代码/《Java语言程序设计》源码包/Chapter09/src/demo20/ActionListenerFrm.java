package demo20;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ActionListenerFrm extends JFrame {

	JButton btn = new JButton("请单击这里！");            // 创建按钮

	public ActionListenerFrm() {
		Container con = getContentPane();
		setTitle("事件监听器窗体");
		setLayout(null);
		con.add(btn);
		//为按钮添加事件监听器
		btn.addActionListener(new BtnAction());
		btn.setBounds(10, 10, 200, 30);
		setSize(400, 300);
		// 设置窗体关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	//定义内部类实现ActionListener接口
	class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			btn.setText("我被单击了");
		}
	}

	public static void main(String[] args) {
		new ActionListenerFrm();
	}
}