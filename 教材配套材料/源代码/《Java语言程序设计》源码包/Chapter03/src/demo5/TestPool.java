package demo5;

class Pool{
  public static int water =3;
  public void inWater(){
    water+=2;
  }
  public void outWater(){
    if(water >=3){
      water-=3;
    }else {
      water=0;
    }
  }
}
public class TestPool {
  public static void main(String[] args) {
    Pool pool =new Pool();
    int i = 1;
    while(Pool.water>0){
      pool.inWater();
      System.out.println("水池注水"+i+"次");
      pool.outWater();
      System.out.println("水池放水"+i+"次");
      System.out.println("水池的水量："+Pool.water);
      i++;
    }
  }
}