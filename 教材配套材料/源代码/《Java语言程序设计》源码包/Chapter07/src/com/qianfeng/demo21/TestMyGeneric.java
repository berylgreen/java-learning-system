package com.qianfeng.demo21;

public class TestMyGeneric {
	public static void main(String[] args) {
		Pool pool = new Pool();
		pool.set(new Boolean(true));
		String i = pool.get();
		System.out.println(i);
	}
}
class Pool {
	Object variable;
	public void set(Object variable) {
		this.variable = variable;
	}
	public Object get() {
		return variable;
	}
}