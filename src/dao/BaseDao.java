package dao;

import java.sql.Connection;
import java.sql.SQLException;

import util.DbUtil;

/**
 * 
 * @author llq
 *创建对数据库连接对象，整个项目与数据库打交道都用这一个对象.
 */
public class BaseDao {
	public Connection con = new DbUtil().getCon();
	//Connection对象是一个连接对象，主要功能是建立与物理数据库的连接
	public void closeDao(){//关闭与数据库的连接
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
