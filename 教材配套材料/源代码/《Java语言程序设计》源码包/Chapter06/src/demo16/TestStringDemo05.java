package demo16;


public class TestStringDemo05 {

	public static void main(String[] args) {
		String str = "1000phone.com";
		//从“.”处进行字符串拆分
		String[] split = str.split("\\.");
		//循环输出拆分后的字符串数组
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
	}
}