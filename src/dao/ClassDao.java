package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.StudentClass;
import util.StringUtil;

/**
 * 
 * 班级信息与数据库的操作
 * @author llq
 *
 */
public class ClassDao extends BaseDao {//继承数据库的连接
	public boolean addClass(StudentClass scl){
		String sql = "insert into s_class values(null,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, scl.getName());//传入班级名称
			preparedStatement.setString(2, scl.getInfo());//传入班级信息
			if(preparedStatement.executeUpdate() > 0)return true;//判断是否输入信息
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<StudentClass> getClassList(StudentClass studentClass){//班级信息 
		List<StudentClass> retList = new ArrayList<StudentClass>();//一个集合，进行查找
		String sqlString = "select * from s_class";//模糊查找的实现
		if(!StringUtil.isEmpty(studentClass.getName())){
			sqlString += " where name like '%"+studentClass.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				StudentClass sc = new StudentClass();//学生信息与班级信息进行连接
				sc.setId(executeQuery.getInt("id"));
				sc.setName(executeQuery.getString("name"));
				sc.setInfo(executeQuery.getString("info"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id){
		String sql = "delete from s_class where id=?";//选择要删除的对象
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
	public boolean update(StudentClass sc){
		String sql = "update s_class set name=?, info=? where id=?";//选择要修改的对象
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sc.getName());
			preparedStatement.setString(2, sc.getInfo());
			preparedStatement.setInt(3, sc.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
