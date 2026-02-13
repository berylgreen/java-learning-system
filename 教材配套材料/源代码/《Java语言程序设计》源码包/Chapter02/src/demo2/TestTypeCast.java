package com.qianfeng.syntax;
public class TestTypeCast{
  public static void main(String[] args) {
    //流浪猫领养平台待领养的流浪猫数量是128只
    int num= 128;
    //int类型的变量guGongArea赋值给int类型的变量area
    byte sNum= (byte) num;
    System.out.println(sNum);
  }
}