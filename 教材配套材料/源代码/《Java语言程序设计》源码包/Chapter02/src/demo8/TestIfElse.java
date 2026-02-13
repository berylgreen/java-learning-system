package demo8;

import java.util.Scanner;

public class TestIfElse {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("是否为本地领养：");
    String answer = input.next();
    if(answer.equals("是")){
      System.out.println("符合领养条件！");
    }else{
      System.out.println("不符合领养条件！");
    }
  }
}