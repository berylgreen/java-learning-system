package demo6;

class Pool2 {
  public static int water = 3;

  public static void outWater() {
    if (water >= 3) {
      water = water - 3;
    } else {
      water = 0;
    }
  }
  public static void inWater() {
    water = water + 2;
  }
}
public class TestPool2 {
  public static void main(String[] args) {
    int i = 1;
    while(Pool2.water>0){
      Pool2.inWater();
      System.out.println("水池注水"+i+"次");
      Pool2.outWater();
      System.out.println("水池放水"+i+"次");
      System.out.println("水池的水量："+ Pool2.water);
      i++;
    }
  }
}