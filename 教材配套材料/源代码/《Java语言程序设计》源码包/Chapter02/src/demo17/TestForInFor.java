package demo17;

public class TestForInFor {

  public static void main(String[] args) {
    for (int row = 1; row <= 9; row++) {//控制输出的行数
      for (int column = 1; column <= row; column++) {//控制列数
        //输出算式，，并用制表符控制上下对齐
        System.out.print(column + "*" + row + "=" + column * row + "\t");
      }
      System.out.println();//每输出完一行之后进行换行
    }
  }
}