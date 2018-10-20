package ren.oliver.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import ren.oliver.jdbc.DatabaseConnectionPool;
import ren.oliver.jdbc.JDBCUtils;

public class TestDatabaseConnectionPool {

	@Test
	public void testAddUser() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 1.创建自定义连接池对象
		DataSource dataSource = new DatabaseConnectionPool();
		try {
			// 2.从池子中获取连接
			conn = dataSource.getConnection();
			String sql = "insert into tbl_user values(null,?,?)";
			//3.必须在自定义的connection类中重写prepareStatement(sql)方法
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "吕布1");
			pstmt.setString(2, "貂蝉1");
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.release(conn, pstmt, null);
		}
	}

}
