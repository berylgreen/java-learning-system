package demo18;

import java.util.Arrays;
import java.util.List;

class Animal {

  @FunctionalInterface
  public interface Supplier<T> {

    T get();
  }

  //Supplier是JDK1.8提供的接口 ，这里和Lambda一起使用了
  public static Animal create(final Supplier<Animal> supplier) {
    return supplier.get();
  }

  public static void collide(final Animal animal) {
    System.out.println("Collided " + animal.toString());
  }

  public void follow(final Animal another) {
    System.out.println("Following " + another.toString());
  }

  public void repair() {
    System.out.println("Repaired " + this.toString());
  }

  public static void main(String[] args) {
//构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
    Animal       animal  = Animal.create(Animal::new);
    Animal       dog     = Animal.create(Animal::new);
    Animal       pig     = Animal.create(Animal::new);
    Animal       bear    = new Animal();
    List<Animal> animals = Arrays.asList(animal, dog, pig, bear);
    System.out.println("===================构造器引用========================");
    //静态方法引用：它的语法是Class::static_method，实例如下：
    animals.forEach(Animal::collide);
    System.out.println("===================静态方法引用========================");
    //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
    animals.forEach(Animal::repair);
    System.out.println("==============特定类的任意对象的方法引用================");
    //特定对象的方法引用：它的语法是instance::method实例如下：
    final Animal duixiang = Animal.create(Animal::new);
    animals.forEach(duixiang::follow);
    System.out.println("===================特定对象的方法引用===================");
  }
}