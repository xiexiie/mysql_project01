 package dao;

import model.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends BaseDao {
	/**
	 * 管理员登陆
	 */
	public Admin login(Admin admin){
		String sql = "select * from s_admin where name=? and password=?";
		Admin adminRst = null;
		try {
	//PreparedStatement  pstm 同样是通过Connection的preparedStatement(sql)方法来创建对象,
	//sql语句可具有一个或者多个输入参数,这些输入参数的值在SQL语句创建时未被指定,而是为每个输入参数保留一个问号'?'作为占位符;
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, admin.getName());//开始对象
			prst.setString(2, admin.getPassword());//数据库密码
			ResultSet executeQuery = prst.executeQuery();//进行查询的语句标志，类似一个文件，用于产生单个结果
//要用statement类的executeQuery()方法来下达select指令以查询数据库，executeQuery()方法
//会把数据库响应的查询结果存放在ResultSet类对象中供我们使用。即语句：ResultSet rs=s.executeQuery(sql);
			if(executeQuery.next()){
				adminRst = new Admin();
				adminRst.setId(executeQuery.getInt("id"));
				adminRst.setName(executeQuery.getString("name"));
				adminRst.setPassword(executeQuery.getString("password"));
				adminRst.setCreateDate(executeQuery.getString("createDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminRst;
	}
	public String editPassword(Admin admin,String newPassword){//编辑密码，在数据库中进行修改密码
		String sql = "select * from s_admin where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);//preparedstatement是预编译得, preparedstatement支持批处理
			prst.setInt(1, admin.getId());
			prst.setString(2, admin.getPassword());
			ResultSet executeQuery = prst.executeQuery();//输入新的密码
			if(!executeQuery.next()){
				String retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//把sql语句传给数据库操作对象
		String retString = "修改失败";
		String sqlString = "update s_admin set password = ? where id = ?";//sql语句进行修改密码
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			//executeUpdate能运行insert，delect,update语句,返回结果是处理的记录的条数
			if(rst > 0){
				retString = "密码修改成功！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把sql语句传给数据库操作对象
		return retString;
	}
}
