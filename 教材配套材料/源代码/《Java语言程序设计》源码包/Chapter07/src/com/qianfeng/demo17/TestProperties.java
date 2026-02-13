package com.qianfeng.demo17;
import java.io.FileOutputStream;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws Exception {
		Properties pro = new Properties();        // 创建Properties对象
		// 向Properties中添加属性
		pro.setProperty("username", "1000phone");
		pro.setProperty("password", "123456");
		// 将Properties中的属性保存到test.txt中
		pro.store(new FileOutputStream("test.ini"), "title");
	}
}