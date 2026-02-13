package demo2;

import java.sql.*;

public class TestPrep {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//1.注册加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取连接对象
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "admin");
			//3.创建语句对象
			PreparedStatement ps = con.prepareStatement("select * from t_student where name =?");
			//设置参数
			ps.setString(1,"张明");
			//4.执行语句
			rs = ps.executeQuery();
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