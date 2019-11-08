package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.Teacher;
import util.StringUtil;

public class TeacherDao extends BaseDao {//添加老师信息到数据库
	public boolean addTeacher(Teacher teacher){
		String sql = "insert into s_teacher values(null,?,?,?,?,?)";//姓名，性别，职位，年龄，密码
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Teacher> getTeacherList(Teacher teacher) {//获取数据库中老师的信息
		// TODO Auto-generated method stub
		List<Teacher> retList = new ArrayList<Teacher>();
		//继承自 AbstractList，实现了 List 接口。底层基于数组实现容量大小动态变化。允许 null 的存在。同时还实现了 
		//RandomAccess、Cloneable、Serializable 接口，所以ArrayList 是支持快速访问、复制、序列化的。
		StringBuffer sqlString = new StringBuffer("select * from s_teacher");
		if(!StringUtil.isEmpty(teacher.getName())){
			sqlString.append(" where name like '%"+teacher.getName()+"%'");
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Teacher t = new Teacher();
				t.setId(executeQuery.getInt("id"));
				t.setName(executeQuery.getString("name"));
				t.setSex(executeQuery.getString("sex"));
				t.setTitle(executeQuery.getString("title"));
				t.setAge(executeQuery.getInt("age"));
				t.setPassword(executeQuery.getString("password"));
				retList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id){//将老师信息从数据库中删除（通过id进行寻找）
		String sql = "delete from s_teacher where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(Teacher teacher){//将修改后的老师的信息传入到数据库
		String sql = "update s_teacher set name=?, sex=?,title=?,age=?,password=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			preparedStatement.setInt(6, teacher.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Teacher login(Teacher teacher){//老师登录将信息传入到数据库
		String sql = "select * from s_teacher where name=? and password=?";
		Teacher teacherRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, teacher.getName());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				teacherRst = new Teacher();
				teacherRst.setId(executeQuery.getInt("id"));
				teacherRst.setName(executeQuery.getString("name"));
				teacherRst.setPassword(executeQuery.getString("password"));
				teacherRst.setSex(executeQuery.getString("sex"));
				teacherRst.setAge(executeQuery.getInt("Age"));
				teacherRst.setTitle(executeQuery.getString("title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacherRst;
	}
	public String editPassword(Teacher teacher,String newPassword){//老师密码验证
		String sql = "select * from s_teacher where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, teacher.getId());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "旧密码错误！";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//作用是让程序可以继续运行而且告诉你程序异常详细位置。
		}//把sql语句传给数据库操作对象
		String retString = "修改失败";
		String sqlString = "update s_teacher set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
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
