package com.qianfeng.demo22;

public class TestMyGeneric2 {
	public static void main(String[] args) {
		Pool<Integer> pool = new Pool<Integer>();
		pool.set(new Integer(3));
		Integer b = pool.get();
		System.out.println(b);
	}
}
class Pool<T> {				// 创建类时，指定泛型类型为T
	T variable;
	// 指定set()方法参数类型为T类型
	public void set(T variable) {
		this.variable = variable;
	}
	// 指定get()方法返回值为T类型
	public T get() {
		return variable;
	}
}