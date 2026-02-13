package demo1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFile {
	public static void main(String[] args) throws IOException {
		//创建File对象
		File file = new File("E:/file.txt");
		//检测File状态的方法
		System.out.println(file.exists() ? "文件存在" : "文件不存在");
		System.out.println(file.canRead() ? "文件可读" : "文件不可读");
		System.out.println(file.canWrite() ? "文件可写" : "文件不可写");
		System.out.println("文件长度：" + file.length() + "Bytes");
		System.out.println("文件最后修改时间："
			+ new SimpleDateFormat("yyyy-MM-dd").format(new Date(file
			.lastModified())));
		//操作file的路径和名称
		System.out.println("文件名：" + file.getName());
		System.out.println("文件路径：" + file.getPath());
		System.out.println("绝对路径：" + file.getAbsolutePath());
		System.out.println("父文件夹名：" + file.getParent());
		System.out.println("规范名称"+file.getCanonicalPath());
		//操作目录的方法
		System.out.println(file.isDirectory() ? "是" : "不是" + "目录");
		System.out.println(file.mkdir());
		System.out.println(file.list());
		//操作文件的方法
		System.out.println(file.isFile() ? "是文件" : "不是文件");
		System.out.println(file.createNewFile());
		File newFile = new File("E:/java.txt");
		System.out.println(file.createNewFile());
		System.out.println(file.delete());
	}
}