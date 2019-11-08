package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddCourseFrm extends JInternalFrame {
	private JTextField courseNameTextField;
	private JTextField studentNumTextField;
	private JComboBox teacherListComboBox;
	private JTextArea courseInfoTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourseFrm frame = new AddCourseFrm();
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
	public AddCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u8BFE\u7A0B");
		setBounds(100, 100, 635, 510);
		
		JLabel label = new JLabel("");
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		courseNameTextField = new JTextField();
		courseNameTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
	    teacherListComboBox = new JComboBox();
		
		JLabel label_3 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_3.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		studentNumTextField = new JTextField();
		studentNumTextField.setColumns(10);
		
		JLabel label_4 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		label_4.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JButton addCourseButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//确认添加按钮事件
				addCourseAct(ae);
			}
		});
		addCourseButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		addCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		JButton resetButton = new JButton("\u91CD\u7F6E\u6309\u94AE");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//重置事件按钮
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
	    courseInfoTextArea = new JTextArea();
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addComponent(label))
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(courseInfoTextArea, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
						.addComponent(studentNumTextField, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
						.addComponent(teacherListComboBox, 0, 173, Short.MAX_VALUE)
						.addComponent(courseNameTextField, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
					.addGap(100))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(113)
					.addComponent(addCourseButton)
					.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
					.addComponent(resetButton)
					.addGap(131))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2)
							.addGap(49))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(teacherListComboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(40)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(label_4))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addCourseButton)
						.addComponent(resetButton))
					.addGap(53))
		);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();//调用函数

	}

	protected void resetValue(ActionEvent ae) {
		// TODO Auto-generated method stub
		courseNameTextField.setText("");
		courseInfoTextArea.setText("");
		studentNumTextField.setText("");
		teacherListComboBox.setSelectedIndex(0);
	}

	protected void addCourseAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		String courseName=courseNameTextField.getText().toString();
		String CourseInfo=courseInfoTextArea.getText().toString();
		Teacher selectedTeacher=(Teacher) teacherListComboBox.getSelectedItem();
		int studentMaxNum=0;
		try {
			studentMaxNum=Integer.parseInt(studentNumTextField.getText());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "学生人数只能输入数字！");
			return;
		}
		if(StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "请输入课程名称！");
			return;
		}
		if(studentMaxNum<=0) {
			JOptionPane.showMessageDialog(this, "学生人数只能输入大于0的数字！");
			return;
		}
		Course course=new Course();
		course.setName(courseName);
		course.setMax_student_num(studentMaxNum);
		course.setInfo(CourseInfo);
		course.setTeacher_id(selectedTeacher.getId());
		CourseDao courseDao=new CourseDao();
		if(courseDao.addCourse(course)) {
			JOptionPane.showMessageDialog(this, "添加成功");
		}else {
			JOptionPane.showMessageDialog(this, "添加失败");
		}
		courseDao.closeDao();
		resetValue(ae);
		
	}
	
	private void setTeacherCombox(){
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for (Teacher teacher : teacherList) {
			teacherListComboBox.addItem(teacher);
		}
		
	}
}
