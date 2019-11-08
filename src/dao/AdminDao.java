 package dao;

import model.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends BaseDao {
	/**
	 * ����Ա��½
	 */
	public Admin login(Admin admin){
		String sql = "select * from s_admin where name=? and password=?";
		Admin adminRst = null;
		try {
	//PreparedStatement  pstm ͬ����ͨ��Connection��preparedStatement(sql)��������������,
	//sql���ɾ���һ�����߶���������,��Щ���������ֵ��SQL��䴴��ʱδ��ָ��,����Ϊÿ�������������һ���ʺ�'?'��Ϊռλ��;
			PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
			prst.setString(1, admin.getName());//��ʼ����
			prst.setString(2, admin.getPassword());//���ݿ�����
			ResultSet executeQuery = prst.executeQuery();//���в�ѯ������־������һ���ļ������ڲ����������
//Ҫ��statement���executeQuery()�������´�selectָ���Բ�ѯ���ݿ⣬executeQuery()����
//������ݿ���Ӧ�Ĳ�ѯ��������ResultSet������й�����ʹ�á�����䣺ResultSet rs=s.executeQuery(sql);
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
	public String editPassword(Admin admin,String newPassword){//�༭���룬�����ݿ��н����޸�����
		String sql = "select * from s_admin where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);//preparedstatement��Ԥ�����, preparedstatement֧��������
			prst.setInt(1, admin.getId());
			prst.setString(2, admin.getPassword());
			ResultSet executeQuery = prst.executeQuery();//�����µ�����
			if(!executeQuery.next()){
				String retString = "���������";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//��sql��䴫�����ݿ��������
		String retString = "�޸�ʧ��";
		String sqlString = "update s_admin set password = ? where id = ?";//sql�������޸�����
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			//executeUpdate������insert��delect,update���,���ؽ���Ǵ���ļ�¼������
			if(rst > 0){
				retString = "�����޸ĳɹ���";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��sql��䴫�����ݿ��������
		return retString;
	}
}
