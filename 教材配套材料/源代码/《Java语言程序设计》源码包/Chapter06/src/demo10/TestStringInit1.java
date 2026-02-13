package demo10;

public class TestStringInit1 {

	public static void main(String[] args) {
		String str1 = "1000phone";
		String str2 = "1000phone";
		String str3 = "1000" + "phone";
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