package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.SelectedCourseDao;
import dao.StudentDao;
import model.Course;
import model.SelectedCourse;
import model.Student;

public class ManageSelectedCourseFrm extends JInternalFrame {
	private JTable selectedCourseListTable;
	private JComboBox searchCourseComboBox;
	private JComboBox searchStudentComboBox;
	private JComboBox editSelectedStudentComboBox;
	private JComboBox editSelectedCourseComboBox;
	private List<Student> studentList = new ArrayList<Student>();
	private List<Course> courseList = new ArrayList<Course>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSelectedCourseFrm frame = new ManageSelectedCourseFrm();
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
	public ManageSelectedCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u9009\u8BFE\u7BA1\u7406");
		setBounds(100, 100, 669, 562);
		
		JLabel label = new JLabel("\u5B66\u751F\uFF1A");
		label.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchStudentComboBox = new JComboBox();
		searchStudentComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {//
				studentChangeAct(ie);
			}
		});
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		label_1.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u65B0\u4EBA\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchCourseComboBox = new JComboBox();
		
		JButton confirmSelectedButton = new JButton("\u786E\u8BA4\u9009\u8BFE");
		confirmSelectedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//
				confirmSelectedCourse(ae);
			}
		});
		confirmSelectedButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		confirmSelectedButton.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FEE\u6539\u9009\u8BFE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(label)
					.addGap(2)
					.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(label_1)
					.addGap(12)
					.addComponent(searchCourseComboBox, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(confirmSelectedButton)
					.addContainerGap(49, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(75)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(label_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(searchCourseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(confirmSelectedButton)))
					.addGap(47)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		selectedCourseListTable = new JTable();
		selectedCourseListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {//选择课程
				selectedCourse(me);
			}
		});
		selectedCourseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u9009\u8BFE\u7F16\u53F7", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(selectedCourseListTable);
		
		JLabel label_2 = new JLabel("\u5B66\u751F\uFF1A");
		label_2.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editSelectedStudentComboBox = new JComboBox();
		
		JLabel label_3 = new JLabel("\u8BFE\u7A0B\uFF1A");
		label_3.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u65B0\u4EBA\u8BFE\u7A0B.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editSelectedCourseComboBox = new JComboBox();
		
		JButton confirmEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		confirmEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//确定修改按钮事件
				editSelectCourseAct(ae);
			}
		});
		confirmEditButton.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		confirmEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton deleteCourseButton = new JButton("\u9000\u9009\u8BFE\u7A0B");
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//删除课程事件
				deleteSelectedCourse(ae);
			}
		});
		deleteCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteCourseButton.setIcon(new ImageIcon(ManageSelectedCourseFrm.class.getResource("/images/\u5220\u9664.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_2)
							.addGap(18)
							.addComponent(editSelectedStudentComboBox, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(75)
							.addComponent(confirmEditButton)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(39)
							.addComponent(label_3)
							.addGap(18)
							.addComponent(editSelectedCourseComboBox, 0, 140, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(65)
							.addComponent(deleteCourseButton)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(editSelectedStudentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(editSelectedCourseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmEditButton)
						.addComponent(deleteCourseButton))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setStudentCombox();
		setCourseCombox();
		initTable();
		setAuthority();//调用权限
	}
	protected void deleteSelectedCourse(ActionEvent ae) {//删除选择课程
		// TODO Auto-generated method stub
		int row = selectedCourseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择要修改的数据！");
			return;
		}
		int selected_id = Integer.parseInt(selectedCourseListTable.getValueAt(row, 0).toString());
		String courseName = selectedCourseListTable.getValueAt(row, 2).toString();
		SelectedCourse sc = new SelectedCourse();
		sc.setId(selected_id);
		SelectedCourseDao scDao = new SelectedCourseDao();
		CourseDao courseDao = new CourseDao();
		if(scDao.delete(selected_id)){
			if(courseDao.updateSelectedNum(getCourseIdByName(courseName), -1)){
				JOptionPane.showMessageDialog(this, "退课成功！");
			}else{
				JOptionPane.showMessageDialog(this, "退课成功，更新课程信息失败！");
			}
		}else{
			JOptionPane.showMessageDialog(this, "退课失败！");
		}
		scDao.closeDao();
		courseDao.closeDao();
		initTable();
	}

	protected void editSelectCourseAct(ActionEvent ae) {//确定修改事件
		// TODO Auto-generated method stub
		int row = selectedCourseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择要修改的数据！");
			return;
		}
		//获取ID，姓名，课程名，复选框中的学生，课程
		int selected_id = Integer.parseInt(selectedCourseListTable.getValueAt(row, 0).toString());
		String studentName = selectedCourseListTable.getValueAt(row, 1).toString();
		String courseName = selectedCourseListTable.getValueAt(row, 2).toString();
		Student student = (Student) editSelectedStudentComboBox.getSelectedItem();
		Course course = (Course) editSelectedCourseComboBox.getSelectedItem();
		if(studentName.equals(student.getName())&&courseName.equals(course.getName())){
			JOptionPane.showMessageDialog(this, "还未修改数据！");
			return;
		}
		//判断人数是否已满
		CourseDao courseDao = new CourseDao();
		if(!courseDao.selectedEnable(course.getId())){
			JOptionPane.showMessageDialog(this, "该课程已经选满，不能再选!");
			return;
		}
		
		//进行修选择
		SelectedCourse sc = new SelectedCourse();
		sc.setId(selected_id);
		sc.setStudent_id(student.getId());
		sc.setCourse_id(course.getId());
		SelectedCourseDao scDao = new SelectedCourseDao();
		
		if(scDao.isSelected(sc)){
			JOptionPane.showMessageDialog(this, "已经选过这门课程了，不能再选!");
			return;
		}
		if(scDao.updateSelectedCourse(sc)){
			if(courseDao.updateSelectedNum(sc.getCourse_id(),1)){
				if(courseDao.updateSelectedNum(getCourseIdByName(courseName), -1)){//课程减少，退选
					JOptionPane.showMessageDialog(this, "修改成功！!");
				}
			}else{
				JOptionPane.showMessageDialog(this, "修改成功，课程信息更新失败!");
			}
		}
		courseDao.closeDao();
		scDao.closeDao();
		initTable();
	}

	//当点击某一行时将学生的相关信息显示到下面对应的栏目中
	protected void selectedCourse(MouseEvent me) {
		// TODO Auto-generated method stub
		int row = selectedCourseListTable.getSelectedRow();//获取所在行
		String studentName = selectedCourseListTable.getValueAt(row, 1).toString();
		String courseName = selectedCourseListTable.getValueAt(row, 2).toString();
		for (int i = 0; i < editSelectedStudentComboBox.getItemCount();i++) {
			Student student = (Student) editSelectedStudentComboBox.getItemAt(i);
			if(studentName.equals(student.getName())){//学生匹配成功
				editSelectedStudentComboBox.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < editSelectedCourseComboBox.getItemCount();i++) {
			Course course = (Course) editSelectedCourseComboBox.getItemAt(i);
			if(courseName.equals(course.getName())){//班级匹配成功
				editSelectedCourseComboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	protected void studentChangeAct(ItemEvent ie) {//学生姓名复选框中的姓名选中
		// TODO Auto-generated method stub
		if(ie.getStateChange() == ItemEvent.SELECTED){//判断选中的学生，将信息显示
			initTable();
		}
		//JOptionPane.showMessageDialog(this, ie.getStateChange());
	}

	protected void confirmSelectedCourse(ActionEvent ae) {//学生添加课程
		// TODO Auto-generated method stub
		Student sstudent = (Student) searchStudentComboBox.getSelectedItem();
		Course scourse = (Course) searchCourseComboBox.getSelectedItem();
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(sstudent.getId());
		sc.setCourse_id(scourse.getId());
		CourseDao courseDao = new CourseDao();
		if(!courseDao.selectedEnable(scourse.getId())){
			JOptionPane.showMessageDialog(this, "该课程已经选满，不能再选!");
			return;
		}
		SelectedCourseDao scDao = new SelectedCourseDao();
		if(scDao.isSelected(sc)){
			JOptionPane.showMessageDialog(this, "已经选过这门课程了，不能再选!");
			return;
		}
		if(scDao.addSelectedCourse(sc)){
			if(courseDao.updateSelectedNum(sc.getCourse_id(),1)){
				JOptionPane.showMessageDialog(this, "选课成功！!");
			}else{
				JOptionPane.showMessageDialog(this, "选课成功，课程信息更新失败!");
			}
		}else{
			JOptionPane.showMessageDialog(this, "选课失败!");
		}
		courseDao.closeDao();
		scDao.closeDao();
		initTable();
	}

	private void setStudentCombox(){//学生显示的复选框
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		studentDao.closeDao();
		for (Student student : studentList) {
			searchStudentComboBox.addItem(student);
			editSelectedStudentComboBox.addItem(student);
		}
		//每个学生的权限
		if("学生".equals(MainFrm.userType.getName())){
			Student user = (Student) MainFrm.userObject;
			for(int i = 0; i < searchStudentComboBox.getItemCount();i++){
				Student student = (Student) searchStudentComboBox.getItemAt(i);
				if(student.getId() == user.getId()){
					searchStudentComboBox.setSelectedIndex(i);
					editSelectedStudentComboBox.setSelectedIndex(i);
					break;
				}
			}
		}
	}
	private void setCourseCombox(){//课程显示的复选框
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for (Course course : courseList) {
			searchCourseComboBox.addItem(course);
			editSelectedCourseComboBox.addItem(course);
		}
	}
	private void getSelectedCourse(SelectedCourse selectedCourse){//
		SelectedCourseDao selectedCourseDao = new SelectedCourseDao();
		List<SelectedCourse> selectedCourseList = selectedCourseDao.getSelectedCourseList(selectedCourse);
		DefaultTableModel dft = (DefaultTableModel) selectedCourseListTable.getModel();
		dft.setRowCount(0);
		for (SelectedCourse sc : selectedCourseList) {
			Vector v = new Vector();
			v.add(sc.getId());
			v.add(getStudentNameById(sc.getStudent_id()));
			v.add(getCourseNameById(sc.getCourse_id()));
			dft.addRow(v);
		}
		selectedCourseDao.closeDao();
	}
	private void initTable(){//获取学生信息复选框中的姓名
		Student student = (Student) searchStudentComboBox.getSelectedItem();
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(student.getId());
		getSelectedCourse(sc);
	}
	
	////获取学生姓名ID，获取班级ID，显示到框中
	private String getStudentNameById(int id){//获取学生姓名ID
		for (int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getId() == id)
				return studentList.get(i).getName();
		}
		return "";
	}
	private String getCourseNameById(int id){//获取班级ID
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())
				return courseList.get(i).getName();
		}
		return "";
	}
	private int getCourseIdByName(String name){//获取课程ID
		for (int i = 0; i < courseList.size(); i++) {
			if(name.equals(courseList.get(i).getName()))
				return courseList.get(i).getId();
		}
		return 0;
	}
	
	
	private void setAuthority(){//学生课程选择权限
		if("学生".equals(MainFrm.userType.getName())){
			searchStudentComboBox.setEnabled(false);
			editSelectedStudentComboBox.setEnabled(false);
		}
	}
}
