package demo18;

public class TestBreak {

  public static void main(String[] args) {
    int count = 0;//计数器
    for (int i = 1; i < 100; i++) {
      if (i % 13 == 0) {
        System.out.println(i);
        count++;
      }
      if (count == 5) {
        break;//终止当前循环
      }
    }
  }
}