package demo17;

import java.io.*;
public class TestObjectOutputStream {
	public static void main(String[] args) throws Exception {
		Student s = new Student(10, "Lily");
		// 创建文件输出流对象，将数据写入student.txt文件
		FileOutputStream fos = new FileOutputStream("src/student.txt");
		// 创建对象输出流对象
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);			// 将s对象序列化
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