package demo3;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class TestFilter {
	public static void main(String[] args) throws IOException {
		// 匿名类
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				File currFile = new File(dir, name);
				if (currFile.isFile() && name.indexOf(".java") != -1) {
					return true;
				} else {
					return false;
				}
			}
		};
		// 返回目录下扩展名为.java的文件名
		String[] list = new File("E:/IdeaProjects/Chapter08/src").list(filter);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
}