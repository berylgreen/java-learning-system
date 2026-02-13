package demo3;

import java.util.Random;

public abstract class BusinessHandeler {

  // 模板方法：去银行办业务的流程
  public final void execute() {
    getRowNumber();
    handle();
    judge();
  }

  //取号
  Random rom = new Random();

  private void getRowNumber() {
    //生成随机号码
    System.out.println("rowNumber-00" + rom.nextInt(10));
  }

  //办理业务
  public abstract void handle(); //抽象的办理业务方法，由子类实现

  //评价
  private void judge() {
    System.out.println("give a praised");
  }
}