package demo13;

import demo6.Animal;

public class TestToString {
  public static void main(String[] args) {
    Animal a = new Animal("大熊",5);
    System.out.println(a.toString());//调用对象的toString方法
    System.out.println(a);//直接打印对象
  }
}