package demo1;

import java.sql.*;

public class StudentCRUD {

	public static void main(String[] args) throws Exception {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//1.注册加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "admin");
			//3.创建语句对象
			st = con.createStatement();
			//4.执行语句

			/*String sql2 = "update  t_student set gender = '女' where name = '李华'";
			int i = st.executeUpdate(sql2);
			System.out.println(i);*/


			String sql = "select * from t_student";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String gender = rs.getString("gender");
				System.out.println(id + "\t" + name + "\t" + birthday + "\t" + gender);
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//5.关闭资源
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}