package demo13;

abstract class Fireworks {
  abstract void boom();
}
public class TestAnonymousInnerClass {
  public static void main(String[] args) {
    new Fireworks() {
      void boom() {
        System.out.println("烟花爆炸，照亮天空");
      }
    }.boom();
  }
}