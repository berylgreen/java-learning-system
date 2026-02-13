package demo3;

public class TestAssignOperator {
  public static void main(String[] args) {
    int a = 5;
    a += 10;
    a = a + 10;
    System.out.println(a);
    short s = 10;
    s += 10;
//    s = s + 10 ;        //编译错误
    System.out.println(s);
  }
}