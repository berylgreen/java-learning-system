package demo22;

import java.awt.*;
import java.awt.event.*;

public class TestMouseEvent {

	public static void main(String[] args) {
		// 创建Frame对象
		Frame f = new Frame("Frame窗口");
		Panel p = new Panel();
		Button b = new Button("按钮");
		p.add(b);
		f.add(p);
		f.setSize(300, 200);        // 设置长和宽
		f.setLocation(500, 200);    // 设置窗口相对位置
		f.setVisible(true);        // 设置为可见

		b.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = e.getButton();
				if (i == MouseEvent.BUTTON1) {
					System.out.println("mouseClicked-->鼠标左键点击"
						+ e.getClickCount() + "次");
				} else if (i == MouseEvent.BUTTON3) {
					System.out.println("mouseClicked-->鼠标右键点击"
						+ e.getClickCount() + "次");
				} else {
					System.out.println("mouseClicked-->鼠标右键滚轴"
						+ e.getClickCount() + "次");
				}
			}


		});


	}
}