package demo15;

public class TestStringDemo04{
 public static void main(String[] args){
		 String str="1000phone.com";
		 //从第 11 个字符开始截取
		 System.out.println(str.substring(10));
		 //截取第 5～9 个字符
		 System.out.println(str.substring(4,9));
 }
 }