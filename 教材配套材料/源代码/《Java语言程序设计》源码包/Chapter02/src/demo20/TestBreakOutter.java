package demo20;

public class TestBreakOutter {

  public static void main(String[] args) {
    boolean used = false;//标记是否可用
    Outer:
    for (int i = 1; i <= 5; i++) {
      for (int j = 1; j <= 10; j++) {
        if (i == 2 && j == 3) {
          used = true;
          System.out.println("第" + i + "排，第 " + j + "个：" + used);
          break Outer;
        } else {
          System.out.println("第" + i + "排，第 " + j + "个：" + used);
        }
      }
    }
  }
}