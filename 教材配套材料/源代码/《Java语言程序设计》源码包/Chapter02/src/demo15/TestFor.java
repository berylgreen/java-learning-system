package demo15;

public class TestFor {

  public static void main(String[] args) {
    int sum = 0;
    int i;
    for (i = 1; i <= 100; i++) {
      sum += i;
    }
    System.out.println("i的最终值：" + i);
    System.out.println("1-100的累加和：" + sum);
  }
}