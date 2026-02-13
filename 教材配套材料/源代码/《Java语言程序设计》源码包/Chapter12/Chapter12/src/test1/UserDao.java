package test1;


import java.sql.*;

public class UserDao {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public Boolean addUser(String name, String pwd, String area, String work, String home)
		throws SQLException {
		try {
			//1.注册加载驱动
			Class.forName("com.mysql.jc.jdbc.Driver");
			//2.获取数据库的链接
			DriverManager.getConnection("jdbc:mysql:///loveadopt", "root", "admin");
			//3.创建语句对象
			String sql = "insert into t_user (`name`,`password`,`area`,`work`,`home`) value(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			//设置参数
			pst.setString(1, name);
			pst.setString(2, pwd);
			pst.setString(3, area);
			pst.setString(4, work);
			pst.setString(5, home);
			//4.执行语句
			int i = pst.executeUpdate();
			if (i == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//5.关闭资源
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return false;
	}

}