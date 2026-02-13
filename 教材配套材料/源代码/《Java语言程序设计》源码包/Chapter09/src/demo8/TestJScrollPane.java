package demo8;
import javax.swing.*;
public class TestJScrollPane {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame窗口");
		// 创建文本区域组件
		JTextArea jta = new JTextArea(20, 50);
		jta.setText("带滚动条的文字编译器");
		JScrollPane jsp = new JScrollPane(jta);
		jf.add(jsp);
		jf.setSize(500, 200);
		// 设置窗体关闭方式
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}