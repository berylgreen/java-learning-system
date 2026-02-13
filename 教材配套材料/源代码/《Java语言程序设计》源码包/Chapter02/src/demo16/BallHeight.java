package demo16;

public class BallHeight {

  public static void main(String[] args) {
    double lon    = 0;  //记录总距离
    double single = 100;//记录每次的高度
    for (int i = 1; i <= 10; i++) {//模拟弹起10次
      //计算本次弹起的高度
      single /= 2;
      //计算总高度
      lon += (single * 2);
    }
    //加上第一次的高度
    System.out.println(lon + 100 + "  " + single);
  }
}