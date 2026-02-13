package com.qianfeng.demo25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class StreamTest {
	public static void main(String[] args) {
		//1、通过集合提供的stream方法或parallelStream()方法创建
		List<String> list = new ArrayList<>();
		Stream<String> stringStream = list.stream();
		//2、通过Arrays中的静态方法stream获取数组流
		Employee[] employees = new Employee[10];
		Stream<Employee> stream = Arrays.stream(employees);
		//3、通过Stream类的静态方法of()创建流
		Stream<String> stream1 = Stream.of("aa","bb","cc");
		//4、创建无限流
		//迭代方式创建无限流
		//从0开始，每次加2，生成无限个
		Stream<Integer> stream2 = Stream.iterate(0,(x) -> x+2);
		//生成10个
		stream2.limit(10).forEach(System.out::println);
		//生成方式创建无限流
		Stream.generate(() -> Math.random())
			.limit(5)
			.forEach(System.out::println);
	}
}