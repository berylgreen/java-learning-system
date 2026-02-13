package demo25;

public class TestTwoDimensionalArray {

  public static void main(String[] args) {
    // 定义二维数组，3行*3列
    int[][] array = new int[3][3];
    // 动态初始化二维数组
    // array.length获取二维数组的元数个数
    // array[i].length获取二维数组元素指向的一维数组个数
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = 3 * i + j + 1;
      }
    }
    // 打印二维数组
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        System.out.print(array[i][j] + "\t");
      }
      System.out.println();
    }
  }
}