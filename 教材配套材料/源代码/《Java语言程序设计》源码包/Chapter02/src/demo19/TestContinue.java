package demo19;

public class TestContinue {

  public static void main(String[] args) {
    int count = 0;//计数器
    for (int i = 1; i <= 100; i++) {
      if (i % 7 == 0 || i % 10 == 7) {
        count++;
        continue;//跳过本次循环中循环体后面的语句
      }
      System.out.println(i);
    }
    System.out.println("共拍手" + count + "次");
  }
}