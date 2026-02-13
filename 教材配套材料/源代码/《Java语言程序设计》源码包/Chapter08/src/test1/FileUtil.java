package test1;

import java.io.*
public class FileUtil {
	/**
	 * 读取文件中的信息
	 */
	public static void getMessage(String address) {
		Reader reader = null;
		StringBuffer buffer = null;
		try {
			reader = new FileReader(address);
			char[] array = new char[3];
			buffer = new StringBuffer();
			int length = reader.read(array);
			while(length != -1) {
				buffer.append(array);
				array = new char[3];
				length = reader.read(array);
			}
		} catch(FileNotFoundException e) {
			System.out.println("科普不见了...");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffer!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(buffer);
	}
	public static void main(String[] args) {
		FileUtil.getMessage("E:\\IdeaProjects\\Chapter08\\src\\knowledge.txt");
	}
}