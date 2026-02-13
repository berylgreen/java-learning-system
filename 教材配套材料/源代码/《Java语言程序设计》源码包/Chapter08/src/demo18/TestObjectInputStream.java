package demo18;

import java.io.*;
public class TestObjectInputStream {
	public static void main(String[] args) throws Exception {
		// 创建文件输入流对象，读取student.txt文件的内容
		FileInputStream fis = new FileInputStream("src/student.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		// 从student.txt文件中读取数据
		Student s = (Student) ois.readObject();
		System.out.println("Student对象的id是  ：" + s.getId());
		System.out.println("Student对象的name是：" + s.getName());
	}
}
class Student implements Serializable {
	private Integer id;
	private String name;

	public Student(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}