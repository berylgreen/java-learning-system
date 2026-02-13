package demo4;

import demo3.BusinessHandeler;

public class SaveMoneyHandler extends BusinessHandeler {
  @Override
  public void handle() {
    System.out.println("存1000w人民币。");
  }
  public static void main(String []args){
    SaveMoneyHandler saveMoneyHandler = new SaveMoneyHandler();
    saveMoneyHandler.execute();
  }
}