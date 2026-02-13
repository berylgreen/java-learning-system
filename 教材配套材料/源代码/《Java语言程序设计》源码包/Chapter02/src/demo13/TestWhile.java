package demo13;

public class TestWhile {

  public static void main(String[] args) {
    int sum = 0;
    int i   = 1;
    while (i <= 100) {
      sum += i;
      i++;
    }
    System.out.println("i的最终值：" + i);
    System.out.println("1-100的累加和：" + sum);
  }
}