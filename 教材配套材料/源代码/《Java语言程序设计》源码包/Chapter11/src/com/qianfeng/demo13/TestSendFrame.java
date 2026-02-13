package com.qianfeng.demo13;
import com.qianfeng.demo12.TestSend;
import java.awt.event.*;
import javax.swing.*;
public class TestSendFrame extends JFrame implements ActionListener {
  JTextArea jta;
  JTextField jtf;
  JButton jb;
  JPanel jp;
  String ownerId;
  String friendId;
  TestSend tc;
  public static void main(String[] args) {
    new TestSendFrame();
  }
  public TestSendFrame() {
    setTitle("窗口2");
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
    tc = new TestSend(jta);
    tc.start();
    // 窗体关闭按钮事件
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(null,
            "<html><font size=3>确定退出吗？</html>", "系统提示",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE) == 0) {
          System.exit(0);
          tc.closeSocket();
        } else {
          return;
        }
      }
    });
  }
  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == jb) {
      byte buffer[] = jtf.getText().trim().getBytes();
      tc.sendData(buffer);
    }
  }
}