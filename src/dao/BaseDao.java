package dao;

import java.sql.Connection;
import java.sql.SQLException;

import util.DbUtil;

/**
 * 
 * @author llq
 *���������ݿ����Ӷ���������Ŀ�����ݿ�򽻵�������һ������.
 */
public class BaseDao {
	public Connection con = new DbUtil().getCon();
	//Connection������һ�����Ӷ�����Ҫ�����ǽ������������ݿ������
	public void closeDao(){//�ر������ݿ������
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
