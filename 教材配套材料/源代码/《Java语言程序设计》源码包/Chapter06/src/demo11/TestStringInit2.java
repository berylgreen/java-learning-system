package demo11;

public class TestStringInit2 {

	public static void main(String[] args) {
		String str1 = "1000phone";
		String str2 = new String("1000phone");
		String str3 = new String("1000phone");
		if (str1 == str2) {
			System.out.println("str1 与 str2 相等");
		} else {
			System.out.println("str1 与 str2 不相等");
		}
		if (str2 == str3) {
			System.out.println("str2 与 str3 相等");
		} else {
			System.out.println("str2 与 str3 不相等");
		}
	}
}