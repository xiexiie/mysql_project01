package view;

//学生列表及其内部功能的实现
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import dao.StudentDao;
import model.Student;
import model.StudentClass;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageStudentFrm extends JInternalFrame {
	private JTextField serachStudentNameTextField;
	private JTable studentListTable;
	private JTextField editStudentNameTextField;
	private JPasswordField editStudentPasswordPasswordField;
	private JComboBox searchStudentComboBox;
	private List<StudentClass> studentClassList ;
	private JComboBox editStudentClassComboBox;
	private ButtonGroup editSexButtonGroup;
	private JRadioButton editStudentSexManRadioButton;
	private JRadioButton editStudentSexFemalRadioButton;
	private JRadioButton editStudentSexUnkonwRadioButton;
	private JButton deleteStudentButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStudentFrm frame = new ManageStudentFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 842, 561);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		serachStudentNameTextField = new JTextField();
		serachStudentNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		label_1.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchStudentComboBox = new JComboBox();
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//学生信息列表中的搜索事件
				searchStudent(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label_2.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentNameTextField = new JTextField();
		editStudentNameTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		label_3.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentClassComboBox = new JComboBox();
		
		JLabel label_4 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		label_4.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u6027\u522B.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editSexButtonGroup = new ButtonGroup();
		editStudentSexManRadioButton = new JRadioButton("\u7537");
		editStudentSexManRadioButton.setSelected(true);
		
		editStudentSexFemalRadioButton = new JRadioButton("\u5973");
		
		editStudentSexUnkonwRadioButton = new JRadioButton("\u4FDD\u5BC6");//性别的复选框
		editSexButtonGroup.add(editStudentSexManRadioButton);
		editSexButtonGroup.add(editStudentSexFemalRadioButton);
		editSexButtonGroup.add(editStudentSexUnkonwRadioButton);
		
		JLabel label_5 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_5.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/password.png")));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editStudentPasswordPasswordField = new JPasswordField();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { //确认修改学生信息按钮事件
				submitEditAct(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		deleteStudentButton = new JButton("\u5220\u9664\u5B66\u751F");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//删除学生信息事件
				deleteStudent(ae);
			}
		});
		deleteStudentButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5220\u9664.png")));
		deleteStudentButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(serachStudentNameTextField, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(searchButton)
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_3)
						.addComponent(label_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(editStudentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(editStudentNameTextField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4)
							.addGap(18)
							.addComponent(editStudentSexManRadioButton)
							.addGap(2)
							.addComponent(editStudentSexFemalRadioButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(editStudentSexUnkonwRadioButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_5)
							.addGap(18)
							.addComponent(editStudentPasswordPasswordField)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(submitEditButton)
						.addComponent(deleteStudentButton))
					.addGap(33))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(126, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
					.addGap(123))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(serachStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(searchButton))
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(editStudentSexManRadioButton)
						.addComponent(editStudentSexFemalRadioButton)
						.addComponent(editStudentSexUnkonwRadioButton)
						.addComponent(submitEditButton)
						.addComponent(editStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(editStudentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(editStudentPasswordPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteStudentButton)
						.addComponent(label_3))
					.addGap(20))
		);
		
		studentListTable = new JTable();//学生信息清单框
		studentListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedTableRow(arg0);//调用tableRow方法
			}
		});
		studentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					//表中五列对应的栏目
					"\u5B66\u751F\u7F16\u53F7", "\u5B66\u751F\u59D3\u540D", "\u6240\u5C5E\u73ED\u7EA7", "\u5B66\u751F\u6027\u522B", "\u767B\u5F55\u5BC6\u7801"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, false//表头部分对应的显示情况
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(studentListTable);//表头
		getContentPane().setLayout(groupLayout);//框架
		setStudentClassInfo();
		setTable(new Student());//将信息显示在表格里，更新表格内容
		setAuthority();//学生用户权限
	}
	protected void submitEditAct(ActionEvent ae) {//确认修改学生信息按钮事件的实现
		// TODO Auto-generated method stub
		int row = studentListTable.getSelectedRow();//选择的行
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		String studentName = editStudentNameTextField.getText().toString();//获取学生姓名
		String studentPassword = editStudentPasswordPasswordField.getText().toString();//获取密码
		if(StringUtil.isEmpty(studentName)){
			JOptionPane.showMessageDialog(this, "请填写学生姓名！");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)){
			JOptionPane.showMessageDialog(this, "请填写密码！");
			return;
		}
		
		Student student = new Student();//将修改后的信息传入
		student.setName(studentName);
		student.setPassword(studentPassword);
		StudentClass sc = (StudentClass)editStudentClassComboBox.getSelectedItem();//强制转换
		student.setClassId(sc.getId());
		student.setId(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()));//获取 IDparseInt() 会将字符串中转换为整数类型
		if(editStudentSexManRadioButton.isSelected())
			student.setSex(editStudentSexManRadioButton.getText().toString());//判断性别进行输入
		if(editStudentSexFemalRadioButton.isSelected())
			student.setSex(editStudentSexFemalRadioButton.getText().toString());
		if(editStudentSexUnkonwRadioButton.isSelected())
		    student.setSex(editStudentSexUnkonwRadioButton.getText().toString());
		StudentDao studentDao = new StudentDao();//数据库中进行修改
		if(studentDao.update(student)){
			JOptionPane.showMessageDialog(this, "更新成功！");
		}else{
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
		studentDao.closeDao();
		setTable(new Student());//刷新表格
	}

	protected void deleteStudent(ActionEvent ae) {//删除学生信息事件的实现
		// TODO Auto-generated method stub
		
		int row = studentListTable.getSelectedRow();
		if(row == -1){//没有选中弹出提示框
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？") != JOptionPane.OK_OPTION){
			return;
		}
		StudentDao studentDao = new StudentDao();                 //从数据库中进行删除
		if(studentDao.delete(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()))){//判断数据库中是否已经删除
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		studentDao.closeDao();
		setTable(new Student());//刷新一下表格
	}
	protected void selectedTableRow(MouseEvent me) {//修改学生信息事件
		// TODO Auto-generated method stub
		DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();//选择列表的行和列，数据库中行和列的选择
		editStudentNameTextField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 1).toString());//姓名
		editStudentPasswordPasswordField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 4).toString());//密码
		String className = dft.getValueAt(studentListTable.getSelectedRow(), 2).toString();
		for(int i=0;i<editStudentClassComboBox.getItemCount();i++){
			StudentClass sc = (StudentClass)editStudentClassComboBox.getItemAt(i);//获取每一行的数据
			if(className.equals(sc.getName())){
				editStudentClassComboBox.setSelectedIndex(i);
			}
		}
		String sex = dft.getValueAt(studentListTable.getSelectedRow(), 3).toString();//学生信息列表中的信息
		editSexButtonGroup.clearSelection();//清空选中
		if(sex.equals(editStudentSexManRadioButton.getText()))//性别比较
			editStudentSexManRadioButton.setSelected(true);//性别男对应的显示
		if(sex.equals(editStudentSexFemalRadioButton.getText()))
			editStudentSexFemalRadioButton.setSelected(true);//性别女对应的显示
		if(sex.equals(editStudentSexUnkonwRadioButton.getText()))
			editStudentSexUnkonwRadioButton.setSelected(true);//保密对应的显示
	}
	protected void searchStudent(ActionEvent ae) {//学生信息列表搜索学生信息事件的实现
		// TODO Auto-generated method stub
		Student student = new Student();//按照student模型中的信息进行搜索
		student.setName(serachStudentNameTextField.getText().toString());//搜索到信息（按照姓名进行查找）
		StudentClass sc = (StudentClass)searchStudentComboBox.getSelectedItem();
		student.setClassId(sc.getId());//ID
		setTable(student);//学生信息表刷新表格
	}

	private void setTable(Student student){//遍历之后将学生信息放入到一个集合中
		if("学生".equals(MainFrm.userType.getName())){
			Student s = (Student)MainFrm.userObject;
			student.setName(s.getName());
		}
		DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();
		dft.setRowCount(0);
		StudentDao studentDao = new StudentDao();
		List<Student> studentList = studentDao.getStudentList(student);
		for (Student s : studentList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(getClassNameById(s.getClassId()));//遍历得到
			v.add(s.getSex());
			v.add(s.getPassword());
			dft.addRow(v);
		}
		studentDao.closeDao();
	}
	private void setStudentClassInfo(){//设置学生信息
		ClassDao classDao = new ClassDao();//按照ClassDao模型进行设置
		studentClassList = classDao.getClassList(new StudentClass());
		for (StudentClass sc : studentClassList) {
			searchStudentComboBox.addItem(sc);
			editStudentClassComboBox.addItem(sc);
		}
		classDao.closeDao();
	}
	private String getClassNameById(int id){//得到班级ID
		for (StudentClass sc : studentClassList) {//遍历复选框得到班级ID
			if(sc.getId() == id)return sc.getName();
		}
		return "";
	}
	private void setAuthority(){//学生用户权限
		//显示学生信息表中各信息的显示
		if("学生".equals(MainFrm.userType.getName())){//班级名称的ID与汉字的转换
			Student s = (Student)MainFrm.userObject;
			serachStudentNameTextField.setText(s.getName());
			serachStudentNameTextField.setEnabled(false);
			deleteStudentButton.setEnabled(false);
			for(int i=0;i<searchStudentComboBox.getItemCount();i++){//遍历学生班级列表，在学生权限时，学生不能修改自己所在的班级
				StudentClass sc = (StudentClass) searchStudentComboBox.getItemAt(i);
				if(sc.getId() == s.getClassId()){//建立两表之间的关系
					searchStudentComboBox.setSelectedIndex(i);
					break;
				}
			}
			searchStudentComboBox.setEnabled(false);
			for(int i=0;i<editStudentClassComboBox.getItemCount();i++){
				StudentClass sc = (StudentClass) editStudentClassComboBox.getItemAt(i);
				if(sc.getId() == s.getClassId()){
					editStudentClassComboBox.setSelectedIndex(i);
					break;
				}
			}
			editStudentClassComboBox.setEnabled(false);//结束所有的按钮，不可继续响应事件
		}
	}
}
