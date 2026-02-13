package com.qianfeng.demo6;

import java.util.HashSet;
import java.util.Scanner;
class User {
	private String username;
	private String password;
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public int hashCode() {
		return username.hashCode();
	}
	public boolean equals(Object obj) {
		User user = (User)obj;
		return this.username.equals(user.username);
	}
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}
public class TestHashSet {
	public static void main(String[] args) {
		HashSet hs = new HashSet();
		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入用户名:");
			String username = scanner.next();
			System.out.print("请输入密码:");
			String password = scanner.next();
			User user = new User(username, password);
			if(hs.add(user)){
				System.out.println("注册成功！");
				System.out.println("已注册用户有"+hs);
			}
			else{
				System.out.println("用户名重复请重新输入！");
			}
		}
	}
}