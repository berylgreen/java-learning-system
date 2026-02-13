package demo21;

public class TestArray {

  public static void main(String[] args) {
    String[] names = new String[]{"小喵", "小面", "翠花"};
    String   name  = names[0];
    System.out.println(name);
    names[0] = "小咪";
    System.out.println(names[0] + "," + names[1] + "," + names[2]);
  }
}