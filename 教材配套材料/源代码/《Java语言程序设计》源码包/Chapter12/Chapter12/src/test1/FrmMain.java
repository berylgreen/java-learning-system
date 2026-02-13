package test1;
import java.awt.*;
import javax.swing.*;

public class FrmMain extends JFrame{
	//菜单栏
	private JMenuBar menubar = new JMenuBar();
	//菜单
	private JMenu menuUser =new JMenu("个人中心");
	private JMenu admin =new JMenu("全部");
	private JMenu inSchool =new JMenu("在校");
	private JMenu graduate =new JMenu("毕业");
	private JMenu dropOut =new JMenu("毕业");
	private JMenu star =new JMenu("喵星");
	//下拉菜单项
	private JMenuItem register =new JMenuItem("注册");
	private JMenuItem login =new JMenuItem("登录");
	private JMenuItem center =new JMenuItem("个人中心");
	//底部状态栏
	private JPanel statusBar = new JPanel();
	//主面板：查询流浪猫信息后显示流浪猫信息
	private JPanel mainPanel = new JPanel();

	public FrmMain() {
		this.setTitle("幸运小猫爱心平台");//设置窗体标题
		this.setSize(700, 500);//设置窗体尺寸
		//添加菜单
		menubar.add(menuUser);
		menubar.add(admin);
		menubar.add(inSchool);
		menubar.add(graduate);
		menubar.add(dropOut);
		menubar.add(star);
		//添加menuUser菜单的下拉菜单项
		menuUser.add(register);
		menuUser.add(login);


		login.addActionListener(e->{
			new FrmLogin(new FrmMain(),"登录", true).setVisible(true);
		});

		register.addActionListener(e->{
			new FrmRegister(new FrmMain(),"注册", true).setVisible(true);
		});



		//设置状态栏
		this.setJMenuBar(menubar);
		//状态栏布局
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("您还没有登录，请登录");
		statusBar.add(label);
		//将窗体转化为容器并添加状态栏
		this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		//设置窗体关闭方式
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//设置窗体位于屏幕中央
	}



	//定义run()方法,设置主窗口可见（使用方法便于进行其他操作）
	public void run()  {
		FrmMain.this.setVisible(true);
	}
}