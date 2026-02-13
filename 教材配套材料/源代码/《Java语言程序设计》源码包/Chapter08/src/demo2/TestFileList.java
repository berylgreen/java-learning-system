package demo2;

import java.io.File;

public class TestFileList {
	public static void main(String[] args) {
		File con = new File("E:\\IdeaProjects");
		fileList(con);
	}
	private static void fileList(File contents){
		//第一级子目录
		File[] files = contents.listFiles();
		for (File file:files) {
			//输出文件和目录
			System.out.println(file);
			//如果子文件夹是目录，则继续递归
			if(file.isDirectory()){
				fileList(file);
			}
		}
	}
}