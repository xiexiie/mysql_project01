package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Student;
import model.StudentClass;
import util.StringUtil;

public class StudentDao extends BaseDao {
	public boolean addStudent(Student student){//数据库中添加学生信息
		String sql = "insert into s_student values(null,?,?,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			//con继承于BaseDao
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getClassId());
			preparedStatement.setString(3, student.getPassword());
			preparedStatement.setString(4, student.getSex());
			if(preparedStatement.executeUpdate() > 0)return true;//executeUpdate(sql)的返回值是更新的条数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<Student> getStudentList(Student student){//按照集合的方式 在数据库中进行查找，然后输出
		List<Student> retList = new ArrayList<Student>();
		//StringBuffer数据空中进行的操作
		StringBuffer sqlString = new StringBuffer("select * from s_student");
		if(!StringUtil.isEmpty(student.getName())){
			sqlString.append(" and name like '%"+student.getName()+"%'");
		}
		if(student.getClassId() != 0){
			sqlString.append(" and classId ="+student.getClassId());
		}
		try {
			//我们绝对应该使用包含参数化的查询语句的prepared statement。这样数据库就会重用准备过的存取方案。缓存适用于整个数据库，
			//所以，如果你安排所有的应用程序使用相同的参数化SQL语句，然后你的其他应用程序就可以重用被准备过的prepared statement。
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Student s = new Student();//数据库中得到学生信息然后传到学生信息栏中
				s.setId(executeQuery.getInt("id"));
				s.setName(executeQuery.getString("name"));
				s.setClassId(executeQuery.getInt("classId"));
				s.setSex(executeQuery.getString("sex"));
				s.setPassword(executeQuery.getString("password"));
				retList.add(s);//循环遍历
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id){//数据库中删除学生信息
		String sql = "delete from s_student where id=?";
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
	public boolean update(Student student){//数据库中修改学生信息
		String sql = "update s_student set name=?, classId=?,sex=?,password=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getClassId());
			preparedStatement.setString(3, student.getSex());
			preparedStatement.setString(4, student.getPassword());
			preparedStatement.setInt(5, student.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String editPassword(Student student,String newPassword){//修改新学生的密码用于登录
		String sql = "select * from s_student where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, student.getId());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
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
		String sqlString = "update s_student set password = ? where id = ?";
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
	public Student login(Student student){//学生登录
		String sql = "select * from s_student where name=? and password=?";
		Student studentRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, student.getName());
			prst.setString(2, student.getPassword());
			ResultSet executeQuery = prst.executeQuery();
//ResultSet 接口提供对数据表的访问。ResultSet 对象通常是通过执行“语句”来生成的。 
//ResultSet 始终有一个游标指向其当前数据行。最初，游标定位在第一行的前面。next() 方法将游标移至下一行			
			if(executeQuery.next()){
				studentRst = new Student();
				studentRst.setId(executeQuery.getInt("id"));
				studentRst.setClassId(executeQuery.getInt("classId"));
				studentRst.setName(executeQuery.getString("name"));
				studentRst.setPassword(executeQuery.getString("password"));
				studentRst.setSex(executeQuery.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentRst;
	}
}
