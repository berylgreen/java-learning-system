package demo12;

public class TestStringDemo01 {

	public static void main(String[] args) {
		String str = "1000phone.com"; //定义字符串
		char[] c = str.toCharArray(); //字符串转换为字符数组
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + "*"); //循环输出
		}
	}
}