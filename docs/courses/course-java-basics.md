# Java 程序设计基础与实战

## 第一章 Java 语言入门

### 1.1 Hello World - 第一个 Java 程序

Java 是一种面向对象的编程语言，由 Sun Microsystems 于 1995 年推出。Java 程序的执行从 `main()` 方法开始。

`System.out.println()` 是 Java 中最常用的输出语句，用于在控制台打印一行文本。

**要点：**

- 每个 Java 程序至少包含一个类
- `main()` 方法是程序的入口
- 语句以分号 `;` 结尾

```java
System.out.println("Hello World! 欢迎学习 Java 程序设计！");
```

### 1.2 Java 程序结构与注释

Java 支持三种注释方式：

- **单行注释**：`//` 后的内容为注释
- **多行注释**：`/* ... */` 之间的内容为注释
- **文档注释**：`/** ... */` 用于生成 API 文档

Java 源文件以 `.java` 为扩展名，编译后生成 `.class` 字节码文件。

```java
// 这是单行注释
/* 这是多行注释 */
String message = "Java 支持三种注释方式";
System.out.println(message);
```

## 第二章 Java 基本语法

### 2.1 数据类型与变量

Java 的基本数据类型分为四大类：

- **整数类型**：`byte`(1字节)、`short`(2字节)、`int`(4字节)、`long`(8字节)
- **浮点类型**：`float`(4字节)、`double`(8字节)
- **字符类型**：`char`(2字节，Unicode 编码)
- **布尔类型**：`boolean`（true/false）

变量必须先声明再使用，声明时需指定数据类型。

```java
int age = 20;
double score = 95.5;
char grade = 'A';
boolean passed = true;
String name = "张三";
System.out.println("姓名：" + name + "，年龄：" + age + "，成绩：" + score + "，等级：" + grade + "，是否通过：" + passed);
```

### 2.2 类型转换

Java 中类型转换分为两种：

- **自动类型转换（隐式）**：小范围类型自动转为大范围类型，如 `int → double`
- **强制类型转换（显式）**：大范围类型转小范围类型，需要强制转换，可能丢失精度

转换规则：`byte → short → int → long → float → double`

```java
int a = 100;
double b = a;
System.out.println("int 自动转 double: " + b);

double c = 3.14;
int d = (int) c;
System.out.println("double 强制转 int: " + d + " (丢失小数部分)");

char ch = 'A';
int ascii = ch;
System.out.println("字符 'A' 的 ASCII 值: " + ascii);
```

### 2.3 运算符

Java 提供丰富的运算符：

- **算术运算符**：`+`、`-`、`*`、`/`、`%`（取模）
- **赋值运算符**：`=`、`+=`、`-=`、`*=`、`/=`
- **比较运算符**：`==`、`!=`、`>`、`<`、`>=`、`<=`
- **逻辑运算符**：`&&`（与）、`||`（或）、`!`（非）
- **自增/自减**：`++`、`--`

注意整数除法会截断小数部分。

```java
int x = 10, y = 3;
System.out.println("加法: " + x + " + " + y + " = " + (x + y));
System.out.println("减法: " + x + " - " + y + " = " + (x - y));
System.out.println("乘法: " + x + " * " + y + " = " + (x * y));
System.out.println("除法: " + x + " / " + y + " = " + (x / y) + " (整数除法)");
System.out.println("取模: " + x + " % " + y + " = " + (x % y));
int a = 5;
a += 3;
System.out.println("a += 3 后 a = " + a);
```

### 2.4 if-else 条件判断

`if-else` 语句用于根据条件执行不同的代码分支：

- `if(条件)` — 条件为 true 时执行
- `if-else` — 二选一
- `if-else if-else` — 多条件分支

```java
int score = 85;
if (score >= 90) {
    System.out.println("优秀");
} else if (score >= 80) {
    System.out.println("良好");
} else if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}
```

### 2.5 switch-case 分支语句

`switch` 语句适用于多值匹配的场景，比多个 `if-else` 更简洁。

注意事项：

- 每个 `case` 后通常需要 `break` 防止穿透
- `default` 处理所有未匹配的情况
- 支持 `int`、`char`、`String`、枚举等类型

```java
int day = 3;
switch (day) {
    case 1: System.out.println("星期一"); break;
    case 2: System.out.println("星期二"); break;
    case 3: System.out.println("星期三"); break;
    case 4: System.out.println("星期四"); break;
    case 5: System.out.println("星期五"); break;
    case 6: System.out.println("星期六"); break;
    case 7: System.out.println("星期日"); break;
    default: System.out.println("无效日期");
}
```

### 2.6 while 与 do-while 循环

- **while 循环**：先判断条件，条件为 true 时执行循环体
- **do-while 循环**：先执行一次循环体，再判断条件

区别：`do-while` 至少执行一次。

```java
// while 循环：计算 1+2+...+10
int sum = 0;
int i = 1;
while (i <= 10) {
    sum += i;
    i++;
}
System.out.println("1到10的累加和（while）: " + sum);

// do-while 循环
int count = 0;
do {
    count++;
} while (count < 5);
System.out.println("do-while 循环执行了 " + count + " 次");
```

### 2.7 for 循环

`for` 循环是最常用的循环结构，适合已知循环次数的场景。

语法：`for (初始化; 条件; 更新) { 循环体 }`

嵌套 `for` 循环常用于处理二维数据，如矩阵、图案等。

```java
// 计算 1-100 的累加和
int sum = 0;
for (int i = 1; i <= 100; i++) {
    sum += i;
}
System.out.println("1到100的累加和: " + sum);

// 打印九九乘法表（前3行）
for (int i = 1; i <= 3; i++) {
    StringBuilder line = new StringBuilder();
    for (int j = 1; j <= i; j++) {
        line.append(j + "×" + i + "=" + (i * j) + "\t");
    }
    System.out.println(line);
}
```

### 2.8 break 与 continue

- **break**：立即跳出当前循环
- **continue**：跳过本次循环的剩余部分，进入下一次迭代

可以使用标签（label）配合 `break` 跳出外层循环。

```java
// break: 找到第一个能被 7 整除的数
for (int i = 1; i <= 100; i++) {
    if (i % 7 == 0) {
        System.out.println("1-100 中第一个能被 7 整除的数: " + i);
        break;
    }
}

// continue: 打印 1-20 中所有奇数
StringBuilder odds = new StringBuilder("1-20中的奇数: ");
for (int i = 1; i <= 20; i++) {
    if (i % 2 == 0) continue;
    odds.append(i).append(" ");
}
System.out.println(odds);
```

### 2.9 数组

数组是存储**固定长度**、**相同类型**元素的数据结构。

声明方式：

- `int[] arr = new int[5];` — 指定长度
- `int[] arr = {1, 2, 3, 4, 5};` — 直接初始化

数组下标从 0 开始，访问越界会抛出 `ArrayIndexOutOfBoundsException`。

```java
String[] names = {"小喵", "小汪", "翠花"};
System.out.println("数组长度: " + names.length);
System.out.println("第一个元素: " + names[0]);

// 遍历数组
for (int i = 0; i < names.length; i++) {
    System.out.println("names[" + i + "] = " + names[i]);
}
```

### 2.10 冒泡排序

冒泡排序是最经典的排序算法之一。核心思想：

1. 相邻元素两两比较
2. 每轮将最大的元素"冒泡"到末尾
3. 共需 n-1 轮

时间复杂度：O(n²)

```java
int[] arr = {64, 34, 25, 12, 22, 11, 90};
int n = arr.length;
for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
    }
}
StringBuilder sb = new StringBuilder("排序结果: ");
for (int val : arr) sb.append(val).append(" ");
System.out.println(sb);
```

## 第三章 面向对象基础

### 3.1 类与对象

**类（Class）** 是对象的模板，定义了对象的属性（成员变量）和行为（方法）。

**对象（Object）** 是类的实例，通过 `new` 关键字创建。

类的基本组成：

- **成员变量**：描述对象的特征
- **方法**：描述对象的行为
- **构造器**：初始化对象

```java
// 在 JShell 中直接定义类并创建对象
class Cat {
    String name;
    String color;
    double age;
    void sayHello() {
        System.out.println("名字：" + name + " 毛色：" + color + " 年龄：" + age);
    }
}

Cat cat = new Cat();
cat.name = "小喵";
cat.color = "橘色";
cat.age = 2.5;
cat.sayHello();
```

### 3.2 构造器与 this 关键字

**构造器（Constructor）** 用于创建对象时初始化成员变量：

- 构造器名必须与类名相同
- 没有返回值类型
- 可以重载（多个构造器，参数不同）

**this 关键字**：

- `this.属性` — 引用当前对象的成员变量
- `this(参数)` — 调用本类其他构造器

```java
class Student {
    String name;
    int age;
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    void introduce() {
        System.out.println("我叫" + this.name + "，今年" + this.age + "岁");
    }
}

Student s1 = new Student("张三", 20);
Student s2 = new Student("李四", 22);
s1.introduce();
s2.introduce();
```

### 3.3 static 关键字

`static` 修饰的成员属于**类**而非对象：

- **静态变量**：所有对象共享同一份数据
- **静态方法**：通过类名直接调用，不能访问非静态成员
- **静态代码块**：类加载时执行一次

```java
class Counter {
    static int count = 0;
    String name;
    Counter(String name) {
        this.name = name;
        count++;
    }
    static void showCount() {
        System.out.println("已创建 " + count + " 个对象");
    }
}

Counter c1 = new Counter("A");
Counter c2 = new Counter("B");
Counter c3 = new Counter("C");
Counter.showCount();
```

### 3.4 方法重载

**方法重载（Overloading）**：同一个类中，方法名相同但参数列表不同。

判断重载的条件：

- 方法名相同
- 参数类型、个数或顺序不同
- 与返回值类型无关

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}

Calculator calc = new Calculator();
System.out.println("int + int = " + calc.add(3, 5));
System.out.println("double + double = " + calc.add(3.14, 2.86));
System.out.println("int + int + int = " + calc.add(1, 2, 3));
```

## 第四章 面向对象进阶

### 4.1 封装

**封装（Encapsulation）** 是面向对象三大特征之一：

- 将属性设为 `private`，隐藏内部实现
- 通过 `public` 的 getter/setter 方法访问
- 可在 setter 中添加数据验证逻辑

访问修饰符：`private` < 默认 < `protected` < `public`

```java
class BankAccount {
    private String owner;
    private double balance;
    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance > 0 ? balance : 0;
    }
    public double getBalance() { return balance; }
    public void deposit(double amount) {
        if (amount > 0) { balance += amount; System.out.println("存入 " + amount + "，余额: " + balance); }
        else System.out.println("存款金额必须大于0");
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { balance -= amount; System.out.println("取出 " + amount + "，余额: " + balance); }
        else System.out.println("取款失败");
    }
}
BankAccount acc = new BankAccount("张三", 1000);
acc.deposit(500);
acc.withdraw(300);
System.out.println("最终余额: " + acc.getBalance());
```

### 4.2 继承

**继承（Inheritance）** 允许子类复用父类的代码：

- 使用 `extends` 关键字
- 子类继承父类的非 private 成员
- Java 只支持单继承
- 所有类默认继承 `Object`

子类可以**扩展**父类的功能，添加新方法和属性。

```java
class Animal {
    String name;
    double age;
    Animal(String name, double age) { this.name = name; this.age = age; }
    void sayHello() { System.out.println("我是" + name + "，" + age + "岁"); }
}
class Cat extends Animal {
    Cat(String name, double age) { super(name, age); }
    void catchMice() { System.out.println(name + "在抓老鼠！"); }
}
class Dog extends Animal {
    Dog(String name, double age) { super(name, age); }
    void lookDoor() { System.out.println(name + "在看门！"); }
}
Cat cat = new Cat("小喵", 1.5);
cat.sayHello();
cat.catchMice();
Dog dog = new Dog("小汪", 3);
dog.sayHello();
dog.lookDoor();
```

### 4.3 方法重写与多态

**方法重写（Override）**：子类重新定义父类的方法，方法签名必须相同。

**多态（Polymorphism）**：父类引用指向子类对象，运行时自动调用子类的重写方法。

多态的条件：继承 + 重写 + 父类引用指向子类对象

```java
class Shape {
    String type;
    Shape(String type) { this.type = type; }
    double area() { return 0; }
    void display() { System.out.println(type + " 的面积: " + area()); }
}
class Circle extends Shape {
    double radius;
    Circle(double r) { super("圆形"); this.radius = r; }
    double area() { return 3.14159 * radius * radius; }
}
class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) { super("矩形"); this.width = w; this.height = h; }
    double area() { return width * height; }
}

Shape s1 = new Circle(5);
Shape s2 = new Rectangle(4, 6);
s1.display();
s2.display();
```

### 4.4 toString 与 equals 方法

`Object` 类提供的两个常用方法：

- **toString()**：返回对象的字符串表示，`println()` 自动调用
- **equals()**：比较两个对象的内容是否相等（默认比较地址，需重写）

`==` 比较引用地址，`equals()` 比较内容（重写后）。

```java
class Person {
    String name;
    int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    public String toString() { return "Person{name='" + name + "', age=" + age + "}"; }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.name.equals(other.name) && this.age == other.age;
        }
        return false;
    }
}

Person p1 = new Person("张三", 25);
Person p2 = new Person("张三", 25);
System.out.println("p1 = " + p1);
System.out.println("p1 == p2: " + (p1 == p2));
System.out.println("p1.equals(p2): " + p1.equals(p2));
```

### 4.5 final 关键字

`final` 用于限制修改：

- **final 类**：不能被继承
- **final 方法**：不能被子类重写
- **final 变量**：值不可改变（常量），命名全大写

```java
final double PI = 3.14159;
final String GREETING = "Hello";
System.out.println("PI = " + PI);
System.out.println("GREETING = " + GREETING);

// final 变量不可修改，以下会报错：
// PI = 3.14;

// 实际应用：计算圆的面积
double radius = 5;
double area = PI * radius * radius;
System.out.println("半径为 " + radius + " 的圆面积 = " + area);
```

## 第五章 抽象类与接口

### 5.1 抽象类

**抽象类（Abstract Class）**：

- 使用 `abstract` 修饰
- 可以包含抽象方法（无方法体）和普通方法
- 不能被实例化，必须由子类继承并实现抽象方法

适用场景：多个子类有共同行为但具体实现不同。

```java
abstract class Vehicle {
    String brand;
    Vehicle(String brand) { this.brand = brand; }
    abstract void run();
    void info() { System.out.println("品牌: " + brand); }
}
class Car extends Vehicle {
    Car(String brand) { super(brand); }
    void run() { System.out.println(brand + " 汽车在公路上行驶"); }
}
class Boat extends Vehicle {
    Boat(String brand) { super(brand); }
    void run() { System.out.println(brand + " 轮船在水上航行"); }
}
Vehicle v1 = new Car("特斯拉");
Vehicle v2 = new Boat("招商轮船");
v1.info(); v1.run();
v2.info(); v2.run();
```

### 5.2 接口

**接口（Interface）** 定义了一组行为规范：

- 使用 `interface` 关键字定义
- 方法默认 `public abstract`
- 一个类可以实现多个接口（弥补单继承限制）
- Java 8+ 支持 `default` 方法和 `static` 方法

```java
interface Flyable {
    void fly();
}
interface Swimmable {
    void swim();
}
class Duck implements Flyable, Swimmable {
    String name;
    Duck(String name) { this.name = name; }
    public void fly() { System.out.println(name + "在天上飞"); }
    public void swim() { System.out.println(name + "在水里游"); }
}
Duck duck = new Duck("唐老鸭");
duck.fly();
duck.swim();
```

### 5.3 内部类

Java 支持四种内部类：

- **成员内部类**：定义在类的内部
- **静态内部类**：使用 `static` 修饰的内部类
- **局部内部类**：定义在方法内部
- **匿名内部类**：没有名称的类，常用于接口的快速实现

```java
interface Greeting {
    void greet(String name);
}

// 匿名内部类实现接口
Greeting polite = new Greeting() {
    public void greet(String name) {
        System.out.println("您好，" + name + "！很高兴认识您。");
    }
};
polite.greet("张老师");

// 另一种实现
Greeting casual = new Greeting() {
    public void greet(String name) {
        System.out.println("嗨，" + name + "！");
    }
};
casual.greet("小明");
```

### 5.4 Lambda 表达式

Lambda 表达式是 Java 8 引入的简洁语法，用于实现函数式接口（只有一个抽象方法的接口）。

语法：`(参数) -> 表达式` 或 `(参数) -> { 语句块 }`

优点：代码更简洁、支持函数式编程风格。

```java
interface MathOperation {
    int operate(int a, int b);
}

MathOperation add = (a, b) -> a + b;
MathOperation subtract = (a, b) -> a - b;
MathOperation multiply = (a, b) -> a * b;

System.out.println("10 + 5 = " + add.operate(10, 5));
System.out.println("10 - 5 = " + subtract.operate(10, 5));
System.out.println("10 * 5 = " + multiply.operate(10, 5));
```

### 5.5 函数式接口与方法引用

Java 8 内置了常用函数式接口：

- `Predicate<T>` — 判断（返回 boolean）
- `Function<T,R>` — 转换（T → R）
- `Consumer<T>` — 消费（无返回值）
- `Supplier<T>` — 供给（无参数，返回 T）

```java
import java.util.function.*;
import java.util.*;

List<String> names = List.of("张三", "李四", "王五", "赵六", "张伟");

// Predicate: 过滤姓张的
Predicate<String> startsWithZhang = name -> name.startsWith("张");

// Consumer: 打印
Consumer<String> printer = name -> System.out.println("  " + name);

System.out.println("姓张的同学:");
names.stream().filter(startsWithZhang).forEach(printer);

// Function: 名字长度
Function<String, Integer> nameLength = String::length;
System.out.println("'" + names.get(0) + "' 的长度: " + nameLength.apply(names.get(0)));
```

## 第六章 异常处理与常用类

### 6.1 异常概述

Java 异常体系：

- `Throwable` — 所有异常的父类
  - `Error` — JVM 内部严重错误（如 `OutOfMemoryError`），程序无法处理
  - `Exception` — 可处理的异常
    - `RuntimeException` — 非受检异常（编译器不强制处理）
    - 其他 Exception — 受检异常（必须 try-catch 或 throws）

常见运行时异常：`NullPointerException`、`ArrayIndexOutOfBoundsException`、`ArithmeticException`、`ClassCastException`

```java
// 演示常见异常
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("算术异常: " + e.getMessage());
}

try {
    int[] arr = {1, 2, 3};
    System.out.println(arr[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("数组越界: " + e.getMessage());
}

System.out.println("程序继续执行...");
```

### 6.2 try-catch-finally

异常处理结构：

- `try` — 包含可能抛出异常的代码
- `catch` — 捕获并处理异常（可多个）
- `finally` — 无论是否异常都会执行（通常用于释放资源）

**注意**：子类异常的 catch 必须在父类异常之前。

```java
System.out.println("=== try-catch-finally 示例 ===");
try {
    System.out.println("try 块开始");
    int result = 10 / 0;
    System.out.println("这行不会执行");
} catch (ArithmeticException e) {
    System.out.println("catch: 捕获到异常 - " + e.getMessage());
} finally {
    System.out.println("finally: 无论如何都会执行");
}
System.out.println("程序正常继续");
```

### 6.3 throw 与 throws

- **throw**：在方法内部手动抛出异常对象
- **throws**：在方法签名中声明可能抛出的异常

自定义异常可以继承 `Exception`（受检）或 `RuntimeException`（非受检）。

```java
class AgeException extends RuntimeException {
    AgeException(String msg) { super(msg); }
}

int age = -5;
try {
    if (age < 0 || age > 150) {
        throw new AgeException("年龄不合法: " + age);
    }
    System.out.println("年龄: " + age);
} catch (AgeException e) {
    System.out.println("捕获自定义异常: " + e.getMessage());
}
```

### 6.4 String 类常用方法

String 是 Java 中最常用的类，字符串是不可变对象。

常用方法：

- `length()` — 长度
- `charAt(index)` — 获取字符
- `substring(begin, end)` — 截取子串
- `indexOf(str)` — 查找位置
- `toUpperCase()` / `toLowerCase()` — 大小写转换
- `trim()` — 去除首尾空格
- `split(regex)` — 分割
- `replace(old, new)` — 替换

```java
String str = "  Hello, Java World!  ";
System.out.println("原始: '" + str + "'");
System.out.println("trim: '" + str.trim() + "'");
System.out.println("长度: " + str.trim().length());
System.out.println("大写: " + str.trim().toUpperCase());
System.out.println("替换: " + str.trim().replace("Java", "JShell"));
System.out.println("截取: " + str.trim().substring(7, 11));
System.out.println("分割: " + java.util.Arrays.toString(str.trim().split(", ")));
System.out.println("包含Java: " + str.contains("Java"));
```

### 6.5 StringBuilder 与字符串拼接

`StringBuilder` 是可变字符串，适合频繁修改场景，性能优于 `String` 拼接。

常用方法：

- `append()` — 追加
- `insert()` — 插入
- `delete()` — 删除
- `reverse()` — 反转
- `toString()` — 转为 String

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" Java");
sb.append("!");
System.out.println("append: " + sb);

sb.insert(5, ",");
System.out.println("insert: " + sb);

sb.reverse();
System.out.println("reverse: " + sb);

// 性能对比：拼接 1000 个数字
long start = System.currentTimeMillis();
StringBuilder fast = new StringBuilder();
for (int i = 0; i < 1000; i++) fast.append(i);
long end = System.currentTimeMillis();
System.out.println("StringBuilder 拼接1000次, 长度: " + fast.length() + ", 耗时: " + (end - start) + "ms");
```

### 6.6 Math 与 Random

**Math 类**提供数学计算的静态方法：`abs()`、`pow()`、`sqrt()`、`max()`、`min()`、`round()`

**Random 类**用于生成随机数。

```java
System.out.println("绝对值: Math.abs(-10) = " + Math.abs(-10));
System.out.println("幂运算: Math.pow(2, 10) = " + (int)Math.pow(2, 10));
System.out.println("平方根: Math.sqrt(144) = " + (int)Math.sqrt(144));
System.out.println("最大值: Math.max(30, 50) = " + Math.max(30, 50));
System.out.println("四舍五入: Math.round(3.6) = " + Math.round(3.6));
System.out.println("圆周率: Math.PI = " + Math.PI);

// 生成 5 个 1-100 的随机数
java.util.Random random = new java.util.Random(42);
StringBuilder nums = new StringBuilder("随机数: ");
for (int i = 0; i < 5; i++) nums.append(random.nextInt(100) + 1).append(" ");
System.out.println(nums);
```

## 第七章 集合框架

### 7.1 集合概述与 ArrayList

Java 集合框架的核心接口：

- **Collection**：存储单个元素
  - `List`（有序可重复）：ArrayList、LinkedList
  - `Set`（无序不可重复）：HashSet、TreeSet
- **Map**：存储键值对（HashMap、TreeMap）

`ArrayList` 基于动态数组，支持随机访问，增删较慢。

```java
import java.util.*;

ArrayList<String> list = new ArrayList<>();
list.add("Java");
list.add("Python");
list.add("Go");
list.add("Java");
System.out.println("列表: " + list);
System.out.println("大小: " + list.size());
System.out.println("索引1: " + list.get(1));
list.remove("Go");
System.out.println("删除Go后: " + list);
System.out.println("包含Java: " + list.contains("Java"));
```

### 7.2 LinkedList

`LinkedList` 基于双向链表，增删快（O(1)），随机访问慢（O(n)）。

额外支持 `addFirst()`、`addLast()`、`removeFirst()`、`removeLast()` 等方法，可用作队列或栈。

```java
import java.util.*;

LinkedList<String> queue = new LinkedList<>();
// 当作队列使用 (FIFO)
queue.addLast("任务A");
queue.addLast("任务B");
queue.addLast("任务C");
System.out.println("队列: " + queue);
System.out.println("执行: " + queue.removeFirst());
System.out.println("执行: " + queue.removeFirst());
System.out.println("剩余: " + queue);

// 当作栈使用 (LIFO)
LinkedList<String> stack = new LinkedList<>();
stack.push("底部");
stack.push("中间");
stack.push("顶部");
System.out.println("栈: " + stack);
System.out.println("弹出: " + stack.pop());
System.out.println("剩余: " + stack);
```

### 7.3 HashSet 与 TreeSet

- **HashSet**：基于哈希表，无序，不允许重复，查找 O(1)
- **TreeSet**：基于红黑树，元素自动排序，不允许重复，查找 O(log n)

去重原理：`hashCode()` + `equals()`

```java
import java.util.*;

// HashSet 去重
HashSet<String> set = new HashSet<>();
set.add("Java");
set.add("Python");
set.add("Go");
set.add("Java");
System.out.println("HashSet (自动去重): " + set);

// TreeSet 自动排序
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(50);
treeSet.add(20);
treeSet.add(80);
treeSet.add(10);
treeSet.add(60);
System.out.println("TreeSet (自动排序): " + treeSet);
System.out.println("最小值: " + treeSet.first());
System.out.println("最大值: " + treeSet.last());
```

### 7.4 HashMap

`HashMap` 存储键值对（Key-Value），通过哈希表实现：

- Key 不可重复，Value 可以重复
- 允许一个 null 键和多个 null 值
- 无序存储

遍历方式：`keySet()`、`values()`、`entrySet()`

```java
import java.util.*;

HashMap<String, Integer> scores = new HashMap<>();
scores.put("张三", 90);
scores.put("李四", 85);
scores.put("王五", 92);
scores.put("赵六", 78);

System.out.println("成绩表: " + scores);
System.out.println("张三的成绩: " + scores.get("张三"));
System.out.println("是否有李四: " + scores.containsKey("李四"));

// 遍历
System.out.println("--- 遍历 ---");
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
}
```

### 7.5 泛型

**泛型（Generics）** 提供编译期类型安全：

- 避免强制类型转换
- 将类型检查提前到编译期
- 可用于类、接口、方法

语法：`<T>` 表示类型参数，使用时指定具体类型。

```java
// 泛型方法
<T> void printArray(T[] arr) {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < arr.length; i++) {
        sb.append(arr[i]);
        if (i < arr.length - 1) sb.append(", ");
    }
    sb.append("]");
    System.out.println(sb);
}

Integer[] intArr = {1, 2, 3, 4, 5};
String[] strArr = {"Hello", "Java", "泛型"};
System.out.print("Integer 数组: "); printArray(intArr);
System.out.print("String 数组: "); printArray(strArr);
```

### 7.6 Stream API

Java 8 引入的 Stream API 支持函数式风格的集合操作：

- `filter()` — 过滤
- `map()` — 映射转换
- `sorted()` — 排序
- `forEach()` — 遍历
- `collect()` — 收集结果
- `reduce()` — 归约

```java
import java.util.*;
import java.util.stream.*;

List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3);

// 去重 + 排序 + 过滤偶数
List<Integer> result = numbers.stream()
    .distinct()
    .sorted()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
System.out.println("原始: " + numbers);
System.out.println("去重+排序+偶数: " + result);

// 求和
int sum = numbers.stream().reduce(0, Integer::sum);
System.out.println("总和: " + sum);

// 统计
long count = numbers.stream().distinct().count();
System.out.println("不重复元素个数: " + count);
```

## 第八章 IO 流

### 8.1 File 类

`File` 类用于表示文件或目录的路径，提供文件操作方法：

- `exists()` — 判断是否存在
- `isFile()` / `isDirectory()` — 判断类型
- `getName()` / `getPath()` — 获取名称/路径
- `length()` — 文件大小
- `list()` — 列出目录内容

注意：`File` 不能读写文件内容，需要使用 IO 流。

```java
// 在 JShell 中演示 File 的基本方法
// 注意：沙箱环境下文件操作受限，这里仅演示概念
String path = System.getProperty("user.dir");
System.out.println("当前工作目录: " + path);
System.out.println("系统临时目录: " + System.getProperty("java.io.tmpdir"));
System.out.println("文件分隔符: " + System.getProperty("file.separator"));
System.out.println("路径分隔符: " + System.getProperty("path.separator"));
System.out.println("操作系统: " + System.getProperty("os.name"));
```

### 8.2 字节流 InputStream 与 OutputStream

Java IO 流按数据单位分为：

- **字节流**（处理所有文件）：`InputStream` / `OutputStream`
- **字符流**（处理文本文件）：`Reader` / `Writer`

核心方法：

- `read()` — 读取数据
- `write()` — 写入数据
- `close()` — 关闭流（释放资源）

```java
import java.io.*;

// 使用 ByteArrayOutputStream 演示字节流（不涉及文件系统）
ByteArrayOutputStream baos = new ByteArrayOutputStream();
String text = "Hello, Java IO 流！字节流可以处理任意类型的数据。";
baos.write(text.getBytes("UTF-8"));

// 读取
ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
byte[] buffer = new byte[1024];
int len = bais.read(buffer);
System.out.println("读取 " + len + " 个字节");
System.out.println("内容: " + new String(buffer, 0, len, "UTF-8"));

baos.close();
bais.close();
```

### 8.3 字符流 Reader 与 Writer

字符流以字符为单位读写，专用于处理文本数据：

- `FileReader` / `FileWriter` — 文件字符流
- `BufferedReader` / `BufferedWriter` — 缓冲字符流（性能更好）
- `InputStreamReader` / `OutputStreamWriter` — 转换流（字节↔字符）

```java
import java.io.*;

// 使用 StringReader/StringWriter 演示字符流
String source = "第一行：Java 字符流\n第二行：每次读取一个字符\n第三行：Reader/Writer 专用于文本";
StringReader reader = new StringReader(source);
BufferedReader br = new BufferedReader(reader);

StringWriter writer = new StringWriter();
String line;
int lineNum = 0;
while ((line = br.readLine()) != null) {
    lineNum++;
    writer.write("[" + lineNum + "] " + line + "\n");
}
System.out.println("带行号输出:");
System.out.print(writer.toString());
br.close();
writer.close();
```

### 8.4 对象序列化

**序列化（Serialization）**：将对象转换为字节流，用于持久化存储或网络传输。

- 类必须实现 `Serializable` 接口
- `ObjectOutputStream` — 序列化（写入）
- `ObjectInputStream` — 反序列化（读取）
- `transient` 关键字修饰的字段不参与序列化

```java
import java.io.*;

class Hero implements Serializable {
    String name;
    int level;
    transient String password;
    Hero(String name, int level, String password) {
        this.name = name; this.level = level; this.password = password;
    }
    public String toString() {
        return "Hero{name='" + name + "', level=" + level + ", password='" + password + "'}";
    }
}

// 序列化到字节数组
Hero hero = new Hero("亚瑟", 15, "secret123");
System.out.println("序列化前: " + hero);

ByteArrayOutputStream baos = new ByteArrayOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(baos);
oos.writeObject(hero);
oos.close();
System.out.println("序列化字节数: " + baos.size());

// 反序列化
ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
Hero loaded = (Hero) ois.readObject();
ois.close();
System.out.println("反序列化后: " + loaded);
System.out.println("注意: transient 字段 password 为 null");
```

## 第九章 多线程编程

### 9.1 线程创建方式

Java 创建线程的两种基本方式：

1. **继承 Thread 类**：重写 `run()` 方法
2. **实现 Runnable 接口**：实现 `run()` 方法，更灵活（推荐）

`start()` 启动线程（创建新线程并执行 run），直接调用 `run()` 只是普通方法调用。

```java
// 方式1: 继承 Thread
class MyThread extends Thread {
    String task;
    MyThread(String task) { this.task = task; }
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " 执行 " + task + " (" + i + ")");
        }
    }
}

// 方式2: 实现 Runnable (Lambda)
Runnable runnable = () -> {
    for (int i = 0; i < 3; i++) {
        System.out.println(Thread.currentThread().getName() + " Runnable任务 (" + i + ")");
    }
};

MyThread t1 = new MyThread("下载文件");
t1.setName("线程-A");
Thread t2 = new Thread(runnable, "线程-B");
t1.start();
t2.start();
t1.join();
t2.join();
System.out.println("所有线程执行完毕");
```

### 9.2 线程状态与常用方法

线程的生命周期状态：

- **NEW** → `start()` → **RUNNABLE** → 获取CPU → **RUNNING**
- **BLOCKED** / **WAITING** / **TIMED_WAITING** → 等待/阻塞
- **TERMINATED** — 执行完毕

常用方法：

- `sleep(ms)` — 休眠指定毫秒
- `join()` — 等待线程结束
- `yield()` — 让出 CPU 时间片
- `setDaemon(true)` — 设为守护线程

```java
Thread thread = new Thread(() -> {
    System.out.println("线程状态: " + Thread.currentThread().getState());
    System.out.println("线程名称: " + Thread.currentThread().getName());
    System.out.println("线程优先级: " + Thread.currentThread().getPriority());
    System.out.println("是否守护线程: " + Thread.currentThread().isDaemon());
});
thread.setName("演示线程");
thread.setPriority(Thread.MAX_PRIORITY);
System.out.println("启动前状态: " + thread.getState());
thread.start();
thread.join();
System.out.println("结束后状态: " + thread.getState());
```

### 9.3 线程同步

多线程访问共享资源时可能出现**线程安全问题**，解决方案：

- **synchronized 代码块**：`synchronized(锁对象) { }`
- **synchronized 方法**：方法加 `synchronized` 关键字
- **Lock 锁**：`ReentrantLock`，更灵活

```java
class TicketWindow {
    private int tickets = 10;
    synchronized void sell() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出第 " + tickets + " 张票");
            tickets--;
        }
    }
    int remaining() { return tickets; }
}

TicketWindow window = new TicketWindow();
Thread t1 = new Thread(() -> { for (int i = 0; i < 5; i++) window.sell(); }, "窗口A");
Thread t2 = new Thread(() -> { for (int i = 0; i < 5; i++) window.sell(); }, "窗口B");
t1.start(); t2.start();
t1.join(); t2.join();
System.out.println("剩余票数: " + window.remaining());
```

### 9.4 线程池

线程池避免频繁创建/销毁线程的开销：

- `Executors.newFixedThreadPool(n)` — 固定大小线程池
- `Executors.newCachedThreadPool()` — 缓存线程池（自动扩缩）
- `Executors.newSingleThreadExecutor()` — 单线程池

使用后必须调用 `shutdown()` 关闭线程池。

```java
import java.util.concurrent.*;

ExecutorService pool = Executors.newFixedThreadPool(3);
for (int i = 1; i <= 5; i++) {
    final int taskId = i;
    pool.submit(() -> {
        System.out.println(Thread.currentThread().getName() + " 正在执行任务 " + taskId);
    });
}
pool.shutdown();
pool.awaitTermination(5, TimeUnit.SECONDS);
System.out.println("线程池已关闭，所有任务完成");
```

### 9.5 单例模式与线程安全

**单例模式（Singleton）** 确保一个类只有一个实例：

- **饿汉式**：类加载时创建（线程安全）
- **懒汉式**：首次使用时创建（需同步保证线程安全）
- **静态内部类**：延迟加载 + 线程安全（推荐）

```java
// 饿汉式单例
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    private EagerSingleton() { System.out.println("EagerSingleton 被创建"); }
    static EagerSingleton getInstance() { return INSTANCE; }
}

// 验证单例
EagerSingleton s1 = EagerSingleton.getInstance();
EagerSingleton s2 = EagerSingleton.getInstance();
System.out.println("s1 == s2: " + (s1 == s2));
System.out.println("同一个对象: " + (s1.hashCode() == s2.hashCode()));
```

## 课后习题配置（导入用）

- 1.1 Hello World - 第一个 Java 程序：实现 `solution(String input)`，返回 `"Hello, " + input`。
- 1.2 Java 程序结构与注释：实现 `solution(String input)`，删除 `//` 与 `/* */` 注释标记字符后返回结果字符串。
- 2.1 数据类型与变量：实现 `solution(String input)`，输入格式为 `姓名,年龄,成绩`，返回 `姓名:年龄:成绩`。
- 2.2 类型转换：实现 `solution(String input)`，将输入小数字符串转为 `int` 后返回整数部分。
- 2.3 运算符：实现 `solution(String input)`，输入格式为 `a,b`，返回 `a+b,a-b,a*b`。
- 2.4 if-else 条件判断：实现 `solution(String input)`，输入分数字符串，返回 `优秀/良好/及格/不及格`。
- 2.5 switch-case 分支语句：实现 `solution(String input)`，输入 `1-7`，返回对应 `星期一` 到 `星期日`。
- 2.6 while 与 do-while 循环：实现 `solution(String input)`，输入正整数 `n`，返回 `1..n` 的累加和。
- 2.7 for 循环：实现 `solution(String input)`，输入正整数 `n`，返回 `n` 的阶乘。
- 2.8 break 与 continue：实现 `solution(String input)`，输入正整数 `n`，返回 `1..n` 中所有奇数（逗号分隔）。
- 2.9 数组：实现 `solution(String input)`，输入逗号分隔整数数组，返回最大值。
- 2.10 冒泡排序：实现 `solution(String input)`，输入逗号分隔整数，返回升序结果（逗号分隔）。
- 3.1 类与对象：实现 `solution(String input)`，输入 `name,color,age`，返回 `Cat{name=...,color=...,age=...}`。
- 3.2 构造器与 this 关键字：实现 `solution(String input)`，输入 `name,age`，返回 `我叫{name}，今年{age}岁`。
- 3.3 static 关键字：实现 `solution(String input)`，输入当前对象数，返回新增一个对象后的总数。
- 3.4 方法重载：实现 `solution(String input)`，输入 `a,b` 或 `a,b,c`，返回所有数字之和。
- 4.1 封装：实现 `solution(String input)`，输入 `余额,操作,金额`，操作为 `deposit/withdraw`，返回操作后余额。
- 4.2 继承：实现 `solution(String input)`，输入 `cat|dog,名字`，返回该动物的特有行为描述。
- 4.3 方法重写与多态：实现 `solution(String input)`，输入 `circle,r` 或 `rect,w,h`，返回面积（保留两位小数）。
- 4.4 toString 与 equals 方法：实现 `solution(String input)`，输入 `name,age|name,age`，返回两个对象内容是否相等。
- 4.5 final 关键字：实现 `solution(String input)`，输入半径，使用常量 `PI=3.14159` 计算圆面积并返回。
- 5.1 抽象类：实现 `solution(String input)`，输入 `car|boat,brand`，返回对应运行描述。
- 5.2 接口：实现 `solution(String input)`，输入 `fly|swim,name`，返回行为描述。
- 5.3 内部类：实现 `solution(String input)`，输入姓名，返回匿名内部类风格问候语。
- 5.4 Lambda 表达式：实现 `solution(String input)`，输入 `a,b,op`，`op` 为 `add/sub/mul`，返回运算结果。
- 5.5 函数式接口与方法引用：实现 `solution(String input)`，输入逗号分隔姓名列表，返回姓“张”的人数。
- 6.1 异常概述：实现 `solution(String input)`，输入除数，返回 `10/除数`；若除数为 0 返回 `ArithmeticException`。
- 6.2 try-catch-finally：实现 `solution(String input)`，输入整数，计算 `100/input`，异常时返回 `ERROR`，结果后拼接 `|FINALLY`。
- 6.3 throw 与 throws：实现 `solution(String input)`，输入年龄，合法区间 `0-150` 返回 `OK`，否则返回 `年龄不合法`。
- 6.4 String 类常用方法：实现 `solution(String input)`，返回 `trim` 后的大写字符串与长度（格式 `TEXT|LEN`）。
- 6.5 StringBuilder 与字符串拼接：实现 `solution(String input)`，输入逗号分隔单词，拼接后反转并返回。
- 6.6 Math 与 Random：实现 `solution(String input)`，输入整数 `n`，返回 `abs(n),sqrt(abs(n))`（平方根保留两位小数）。
- 7.1 集合概述与 ArrayList：实现 `solution(String input)`，输入逗号分隔元素，返回列表长度与是否包含 `Java`。
- 7.2 LinkedList：实现 `solution(String input)`，输入逗号分隔任务队列，返回出队前两个任务（逗号分隔）。
- 7.3 HashSet 与 TreeSet：实现 `solution(String input)`，输入逗号分隔整数，去重并升序返回。
- 7.4 HashMap：实现 `solution(String input)`，输入 `name=score;...`，返回分数最高的 `name`。
- 7.5 泛型：实现 `solution(String input)`，输入逗号分隔元素，返回第一个元素与总个数（格式 `first|count`）。
- 7.6 Stream API：实现 `solution(String input)`，输入逗号分隔整数，返回去重后偶数升序结果。
- 8.1 File 类：实现 `solution(String input)`，输入文件路径字符串，返回文件名（不含目录）。
- 8.2 字节流 InputStream 与 OutputStream：实现 `solution(String input)`，返回输入字符串 UTF-8 字节长度。
- 8.3 字符流 Reader 与 Writer：实现 `solution(String input)`，输入包含 `\\n` 的文本，返回行数。
- 8.4 对象序列化：实现 `solution(String input)`，输入 `name,level,password`，返回 `name,level`（忽略 password）。
- 9.1 线程创建方式：实现 `solution(String input)`，输入任务数 `n`，返回模拟执行标记 `T1...Tn`。
- 9.2 线程状态与常用方法：实现 `solution(String input)`，返回 `NEW->RUNNABLE->TERMINATED`。
- 9.3 线程同步：实现 `solution(String input)`，输入票数与窗口数 `tickets,workers`，返回最终剩余票数。
- 9.4 线程池：实现 `solution(String input)`，输入 `tasks,poolSize`，返回完成全部任务所需批次数。
- 9.5 单例模式与线程安全：实现 `solution(String input)`，无论输入什么都返回固定实例标识 `SINGLETON_INSTANCE`。
