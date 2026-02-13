package demo23;

public class TestMostValue {

  public static void main(String[] args) {
    int[] score = {88, 62, 12, 100, 28};
    int   max   = 0;            // 最大值
    int   min   = 0;            // 最小值
    max = min = score[0];    // 把第一个元素值赋给max和min
    for (int i = 1; i < score.length; i++) {
      if (score[i] > max) {  // 依次判断后面元素值是否比max大
        max = score[i];        // 如果大，则修改max的值
      }
      if (score[i] < min) {  // 依次判断后面元素值是否比min小
        min = score[i];        // 如果小，则修改min的值
      }
    }
    System.out.println("最大值：" + max);
    System.out.println("最小值：" + min);
  }
}