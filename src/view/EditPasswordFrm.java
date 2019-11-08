package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.TeacherDao;

import model.Teacher;

import dao.StudentDao;
import model.Student;
import view.MainFrm;

import dao.AdminDao;
import model.Admin;
import util.StringUtil;

public class EditPasswordFrm extends JInternalFrame {

	private JPanel contentPane;
	private JPasswordField oldPasswordTextField;//旧密码
	private JPasswordField newPasswordTextField;//新密码
	private JPasswordField confirmPasswordTextField;//确定新密码
	private JLabel currentUserLabel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPasswordFrm frame = new EditPasswordFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EditPasswordFrm() {
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setClosable(true);
		setIconifiable(true);
		JLabel label = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/password.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		oldPasswordTextField = new JPasswordField();//输入旧密码对应的文本框
		oldPasswordTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_1.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u4FEE\u6539\u5BC6\u7801.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		newPasswordTextField = new JPasswordField();//输入新密码对应的文本框
		newPasswordTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_2.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u4FEE\u6539\u5BC6\u7801.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		confirmPasswordTextField = new JPasswordField();//确定新密码对应的文本框
		confirmPasswordTextField.setColumns(10);
		
		JButton submitButton = new JButton("\u786E\u8BA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//修改密码对应的事件
				submitEdit(e);
			}
		});
		submitButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//重置按钮对应的事件
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		label_3.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u7528\u6237\u540D.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		currentUserLabel = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(86)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(submitButton)
							.addGap(61)
							.addComponent(resetButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label_3)
								.addGap(18)
								.addComponent(currentUserLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_1)
										.addComponent(label))
									.addComponent(label_2))
								.addGap(4)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(confirmPasswordTextField, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(newPasswordTextField)
										.addComponent(oldPasswordTextField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(currentUserLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(oldPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(newPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(confirmPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton)
						.addComponent(submitButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);//判断输入的角色进行输出对应的角色
		if("系统管理员".equals(MainFrm.userType.getName())){
			Admin admin = (Admin)MainFrm.userObject;
			currentUserLabel.setText("【系统管理员】" + admin.getName());
		}else if("学生".equals(MainFrm.userType.getName())){
			Student student = (Student)MainFrm.userObject;
			currentUserLabel.setText("【学生】" + student.getName());
		}else{
			Teacher teacher = (Teacher)MainFrm.userObject;
			currentUserLabel.setText("【教师】" + teacher.getName());
		}
	}

	protected void submitEdit(ActionEvent e) {//修改密码事件的实现
		// TODO Auto-generated method stub
		String oldPassword = oldPasswordTextField.getText().toString();//得到旧密码
		String newPassword = newPasswordTextField.getText().toString();//得到新密码
		String conformPassword = confirmPasswordTextField.getText().toString();//得到确定新密码
		if(StringUtil.isEmpty(oldPassword)){
			JOptionPane.showMessageDialog(this, "请填写旧密码！");//判断是否输入信息
			return;
		}
		if(StringUtil.isEmpty(newPassword)){
			JOptionPane.showMessageDialog(this, "请填写新密码！");
			return;
		}
		if(StringUtil.isEmpty(conformPassword)){
			JOptionPane.showMessageDialog(this, "请确认新密码！");
			return;
		}
		if(!newPassword.equals(conformPassword)){
			JOptionPane.showMessageDialog(this, "两次密码输入不一致！");
			return;
		}
		if("系统管理员".equals(MainFrm.userType.getName())){
			AdminDao adminDao = new AdminDao();//将修改后的系统管理员信息传入到数据库
			Admin adminTmp = new Admin();
			Admin admin = (Admin)MainFrm.userObject;
			adminTmp.setName(admin.getName());
			adminTmp.setId(admin.getId());
			adminTmp.setPassword(oldPassword);
			JOptionPane.showMessageDialog(this, adminDao.editPassword(adminTmp, newPassword));
			adminDao.closeDao();//用完就关掉
			return;
		}
		if("学生".equals(MainFrm.userType.getName())){
			StudentDao studentDao = new StudentDao();//将修改后的学生密码传入到数据库
			Student studentTmp = new Student();
			Student student = (Student)MainFrm.userObject;//进行强制转换
			studentTmp.setName(student.getName());
			studentTmp.setPassword(oldPassword);
			studentTmp.setId(student.getId());
			JOptionPane.showMessageDialog(this, studentDao.editPassword(studentTmp, newPassword));//告知用户已经修改
			studentDao.closeDao();
			return;
		}
		if("教师".equals(MainFrm.userType.getName())){
			TeacherDao teacherDao = new TeacherDao();//将修改后的老师的信息传入到数据库
			Teacher teacherTmp = new Teacher();
			Teacher teacher = (Teacher)MainFrm.userObject;
			teacherTmp.setName(teacher.getName());
			teacherTmp.setPassword(oldPassword);
			teacherTmp.setId(teacher.getId());
			JOptionPane.showMessageDialog(this, teacherDao.editPassword(teacherTmp, newPassword));
			teacherDao.closeDao();
			return;
		}
		
	}

	protected void resetValue(ActionEvent ae) {//重置事件的实现
		// TODO Auto-generated method stub
		oldPasswordTextField.setText("");
		newPasswordTextField.setText("");
		confirmPasswordTextField.setText("");
	}
}
