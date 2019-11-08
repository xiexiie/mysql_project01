package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ClassDao;
import dao.StudentDao;
import model.Student;
import model.StudentClass;
import util.StringUtil;

public class AddStudentFrm extends JInternalFrame {
	private JTextField studentNameTextField;
	private JPasswordField studentPasswordField;
	private JComboBox studentClassComboBox;
	private ButtonGroup sexButtonGroup;
	private JRadioButton studentSexManRadioButton;
	private JRadioButton studentSexFemalRadioButton;
	private JRadioButton studentSexUnkonwRadioButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentFrm frame = new AddStudentFrm();
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
	public AddStudentFrm() {
		setClosable(true);//可以关闭窗口
		setIconifiable(true);//可以让窗口图标化
		setTitle("\u6DFB\u52A0\u5B66\u751F");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		label_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentClassComboBox = new JComboBox();
		studentClassComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		
		JLabel label_2 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/password.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentPasswordField = new JPasswordField();
		
		JLabel label_3 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		label_3.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u6027\u522B.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentSexManRadioButton = new JRadioButton("\u7537");
		studentSexManRadioButton.setSelected(true);
		
		studentSexFemalRadioButton = new JRadioButton("\u5973");
		
		studentSexUnkonwRadioButton = new JRadioButton("\u4FDD\u5BC6");
		
		sexButtonGroup = new ButtonGroup();//性别分组进行添加
		sexButtonGroup.add(studentSexManRadioButton);
		sexButtonGroup.add(studentSexFemalRadioButton);
		sexButtonGroup.add(studentSexUnkonwRadioButton);
		
		JButton submitButton = new JButton("\u786E\u8BA4");
		submitButton.addActionListener(new ActionListener() {//增加学生信息事件
			public void actionPerformed(ActionEvent ae) {
				studentAddAct(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//清空学生信息事件
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(studentPasswordField, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(studentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
							.addGap(92))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(resetButton)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(studentSexManRadioButton)
									.addGap(10)
									.addComponent(studentSexFemalRadioButton)
									.addGap(10)
									.addComponent(studentSexUnkonwRadioButton)))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(132)
					.addComponent(submitButton)
					.addContainerGap(221, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(studentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(studentPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(studentSexManRadioButton)
						.addComponent(studentSexUnkonwRadioButton)
						.addComponent(studentSexFemalRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setStudentClassInfo();
	}

	protected void resetValue(ActionEvent ae) {//清空事件的实现。全部使其初始化
		// TODO Auto-generated method stub
		studentNameTextField.setText("");
		studentPasswordField.setText("");
		studentClassComboBox.setSelectedIndex(0);
		sexButtonGroup.clearSelection();
		studentSexManRadioButton.setSelected(true);
	}

	protected void studentAddAct(ActionEvent ae) {//增加学生信息事件的实现
		// TODO Auto-generated method stub
		String studentName = studentNameTextField.getText().toString();//先获取学生的姓名和密码
		String studentPassword = studentPasswordField.getText().toString();
		if(StringUtil.isEmpty(studentName)){                     //判断是否填写学生姓名
			JOptionPane.showMessageDialog(this, "请填写学生姓名!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)){                 //判断是否填写学生密码
			JOptionPane.showMessageDialog(this, "请填写密码!");
			return;
		}
		StudentClass sc = (StudentClass)studentClassComboBox.getSelectedItem();//getSelectedItem()得到的当前选中的 item 所绑定的数据。
		//学生性别的判断输入
		String sex = studentSexManRadioButton.isSelected() ? studentSexManRadioButton.getText() : (studentSexFemalRadioButton.isSelected() ? studentSexFemalRadioButton.getText() : studentSexUnkonwRadioButton.getText());
		Student student = new Student();//传入所添加的学生信息
		student.setName(studentName);
		student.setClassId(sc.getId());//不需要输入，只需进行选择
		student.setPassword(studentPassword);
		student.setSex(sex);
		StudentDao studentDao = new StudentDao();//在数据库中进行添加
		if(studentDao.addStudent(student)){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetValue(ae);//清空
	}
	
	private void setStudentClassInfo(){//建立学生与班级之间的关系
		ClassDao classDao = new ClassDao();//数据库中进行遍历
		List<StudentClass> classList = classDao.getClassList(new StudentClass());
		for (StudentClass sc : classList) {//遍历
			studentClassComboBox.addItem(sc);
		}
		classDao.closeDao();
	}
}
