package demo14;


import demo6.Animal;

public class TestEquals {

  public static void main(String[] args) {
    Animal a1 = new Animal("大熊", 5);
    Animal a2 = new Animal("大熊", 5);
    System.out.println(a1.equals(a2));
  }
}