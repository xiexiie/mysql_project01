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
 * �༶��Ϣ�����ݿ�Ĳ���
 * @author llq
 *
 */
public class ClassDao extends BaseDao {//�̳����ݿ������
	public boolean addClass(StudentClass scl){
		String sql = "insert into s_class values(null,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, scl.getName());//����༶����
			preparedStatement.setString(2, scl.getInfo());//����༶��Ϣ
			if(preparedStatement.executeUpdate() > 0)return true;//�ж��Ƿ�������Ϣ
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<StudentClass> getClassList(StudentClass studentClass){//�༶��Ϣ 
		List<StudentClass> retList = new ArrayList<StudentClass>();//һ�����ϣ����в���
		String sqlString = "select * from s_class";//ģ�����ҵ�ʵ��
		if(!StringUtil.isEmpty(studentClass.getName())){
			sqlString += " where name like '%"+studentClass.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				StudentClass sc = new StudentClass();//ѧ����Ϣ��༶��Ϣ��������
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
		String sql = "delete from s_class where id=?";//ѡ��Ҫɾ���Ķ���
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
		String sql = "update s_class set name=?, info=? where id=?";//ѡ��Ҫ�޸ĵĶ���
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