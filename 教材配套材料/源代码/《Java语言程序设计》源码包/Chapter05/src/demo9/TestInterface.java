package demo9;

import demo7.Teacher;
import demo8.Student;

public class TestInterface {

  public static void main(String[] args) {
    Teacher t1 = new Teacher("Amy老师");
    Student s1 = new Student("张三");
    Student s2 = new Student("李四");
    // 通过相应的对象调用相应的方法实现控制台输出结果
    t1.talk();
    s1.talk();
    s2.talk();
    t1.work();
    s1.work();
    s2.work();
  }
}