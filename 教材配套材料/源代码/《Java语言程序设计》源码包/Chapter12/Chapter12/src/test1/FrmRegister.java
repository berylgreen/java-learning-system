package test1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrmRegister extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("注册");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelName = new JLabel("昵称");
	private JLabel labelArea = new JLabel("地区");
	private JLabel labelPhone = new JLabel("电话");
	private JLabel labelPwd = new JLabel("密码");
	private JLabel labelWork = new JLabel("工作稳定");
	private JLabel labelHome = new JLabel("居住稳定");

	private JTextField edtName = new JTextField(20);
	private JTextField edtArea = new JTextField(20);
	private JTextField edtPhone = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	;
	//工作单选按钮组
	private ButtonGroup buttonGroupWork = new ButtonGroup();
	private JRadioButton radioWork1 = new JRadioButton("是");
	private JRadioButton radioWork2 = new JRadioButton("否");
	//住所单选按钮组
	private ButtonGroup buttonGroupHome = new ButtonGroup();
	private JRadioButton radioHome1 = new JRadioButton("是");
	private JRadioButton radioHome2 = new JRadioButton("否");

	public FrmRegister(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		BoxLayout boxLayout = new BoxLayout(workPane, BoxLayout.Y_AXIS);
		workPane.setLayout(boxLayout);
		//姓名
		JPanel jPanel1 = new JPanel();
		jPanel1.add(labelName);
		jPanel1.add(edtName);
		workPane.add(jPanel1);

		//密码
		JPanel jPanel2 = new JPanel();
		jPanel2.add(labelPwd);
		jPanel2.add(edtPwd);
		workPane.add(jPanel2);
		//手机
		JPanel jPanel3 = new JPanel();
		jPanel3.add(labelPhone);
		jPanel3.add(edtPhone);
		workPane.add(jPanel3);
		//地区
		JPanel jPanel4 = new JPanel();
		jPanel4.add(labelArea);
		jPanel4.add(edtArea);
		workPane.add(jPanel4);
		//工作
		radioWork1.setSelected(true);
		radioWork1.setActionCommand(radioWork1.getText());
		buttonGroupWork.add(radioWork1);
		radioWork2.setActionCommand(radioWork2.getText());
		buttonGroupWork.add(radioWork2);

		JPanel jPanel5 = new JPanel();
		jPanel5.add(labelWork);
		jPanel5.add(radioWork1);
		jPanel5.add(radioWork2);
		workPane.add(jPanel5);

		radioHome1.setSelected(true);
		radioHome1.setActionCommand(radioHome1.getText());
		buttonGroupHome.add(radioHome1);
		radioWork2.setActionCommand(radioHome2.getText());
		buttonGroupHome.add(radioHome2);
		JPanel jPanel6 = new JPanel();
		jPanel6.add(labelHome);
		jPanel6.add(radioHome1);
		jPanel6.add(radioHome2);
		workPane.add(jPanel6);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 320);
		this.setLocationRelativeTo(f);
		this.validate();
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
		} else if (e.getSource() == this.btnOk) {
			try {
				String name = edtName.getText();
				String age = edtArea.getText();
				String work = buttonGroupWork.getSelection().getActionCommand();
				String home = buttonGroupHome.getSelection().getActionCommand();
				String password = String.valueOf(edtPwd.getPassword());

				UserDao userDao = new UserDao();
				Boolean result = userDao.addUser(name, age, work, home, password);//注册用户
				if(result == true){
					JOptionPane.showMessageDialog(null, "注册成功");
				}else{
					JOptionPane.showMessageDialog(null, "注册失败");
				}

				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1, "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}