package demo18;

public class TestStringBuffer {

	public static void main(String[] args) {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("He"); //追加 String 类型内容
		sb1.append('l'); //追加 char 类型内容
		sb1.append("lo");
		StringBuffer sb2 = new StringBuffer();
		sb2.append("\t");
		sb2.append("World!");
		sb1.append(sb2);
		System.out.println(sb1); //追加 StringBuffer 类型内容
		sb1.delete(5, 6);
		System.out.println("字符串删除：" + sb1);
		String s = "——";
		sb1.insert(5, s);
		System.out.println("字符串插入：" + sb1);
		sb1.reverse();
		System.out.println("字符串反转：" + sb1);
	}
}