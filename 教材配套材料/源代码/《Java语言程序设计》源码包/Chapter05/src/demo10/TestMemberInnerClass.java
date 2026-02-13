package demo10;

class Computer { // 计算机类

  private String brand; // 计算机品牌

  public Computer(String brand) {
    this.brand = brand; // 给计算机品牌赋值
  }

  class CPU { // CPU类（内部类）

    String model; // CPU型号

    public CPU(String model) {
      this.model = model; // 给CPU型号赋值
    }

    public void run() { // CPU的运行方法
      System.out.println("CPU" + this.model + "运行");
    }
  }

  public void start() { // 启动（计算机）方法
    System.out.println("启动" + this.brand);
  }
}

public class TestMemberInnerClass {

  public static void main(String[] args) {
    // 创建计算机类对象，并为计算机品牌赋值
    Computer computer = new Computer("HUAWEI MateBook");
    computer.start(); // 计算机类对象调用启动（计算机）方法
// 创建CPU类（内部类）对象，并为CPU型号赋值
    Computer.CPU cpu = computer.new CPU(" Intel酷睿i510210U");
    cpu.run(); // CPU类对象调用（CPU）运行方法
  }
}