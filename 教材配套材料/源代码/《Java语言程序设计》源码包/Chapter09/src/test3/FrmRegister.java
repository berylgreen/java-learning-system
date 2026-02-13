package test3;
import java.awt.*;
import javax.swing.*;

public class FrmRegister extends JDialog {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("注册");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("昵称");
	private JLabel labelArea = new JLabel("地区");
	private JLabel labelPwd = new JLabel("密码");
	private JLabel labelWork = new JLabel("工作是否稳定");
	private JLabel labelHome = new JLabel("居住是否稳定");
	private JTextField edtName = new JTextField(20);
	private JTextField edtArea = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JTextField edtWork = new JTextField(15);
	private JTextField edtHome = new JTextField(15);
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
		jPanel1.add(labelName);jPanel1.add(edtName);
		workPane.add(jPanel1);
		JPanel jPanel2 = new JPanel();
		jPanel2.add(labelArea);jPanel2.add(edtArea);
		//密码确认密码
		workPane.add(jPanel2);
		JPanel jPanel3 = new JPanel();
		jPanel3.add(labelPwd);jPanel3.add(edtPwd);
		workPane.add(jPanel3);
		//手机
		JPanel jPanel5 = new JPanel();
		jPanel5.add(labelWork);jPanel5.add(edtWork);
		workPane.add(jPanel5);
		//邮箱
		JPanel jPanel6 = new JPanel();
		jPanel6.add(labelHome);jPanel6.add(edtHome);
		workPane.add(jPanel6);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 320);
		this.setLocationRelativeTo(f);
		this.validate();
	}

}