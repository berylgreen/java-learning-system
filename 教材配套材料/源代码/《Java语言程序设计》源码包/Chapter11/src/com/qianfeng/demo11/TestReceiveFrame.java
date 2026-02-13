package com.qianfeng.demo11;

import com.qianfeng.demo10.TestReceive;
import java.awt.event.*;
import javax.swing.*;
public class TestReceiveFrame extends JFrame implements ActionListener {
  JTextArea jta;
  JTextField jtf;
  JButton jb;
  JPanel jp;
  String ownerId;
  String friendId;
  TestReceive ts;
  public static void main(String[] args) {
    new TestReceiveFrame();
  }
  public TestReceiveFrame() {
    setTitle("窗口1");
    jta = new JTextArea();
    jtf = new JTextField(15);
    jb = new JButton("发送");
    jb.addActionListener(this);
    jp = new JPanel();
    jp.add(jtf);
    jp.add(jb);
    this.add(jta, "Center");
    this.add(jp, "South");
    this.setBounds(300, 200, 300, 200);
    this.setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ts = new TestReceive(jta);
    ts.start();
    // 窗体关闭按钮事件
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(null,
            "<html><font size=3>确定退出吗？</html>", "系统提示",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE) == 0) {
          System.exit(0);
          ts.closeSocket();
        } else {
          return;
        }
      }
    });
  }
  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == jb) {
      byte buffer[] = jtf.getText().trim().getBytes();
      ts.sendData(buffer);
    }
  }
}