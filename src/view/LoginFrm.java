package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.TeacherDao;
import model.Teacher;
import view.MainFrm;

import dao.AdminDao;
import dao.StudentDao;

import model.Admin;
import model.Student;

import model.UserType;
import util.StringUtil;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	private JComboBox userTypeComboBox;

	/**
	 * Launch the application. jawdjxzbcjkl 45546+456+546+56+4E65+4:LGJoDAok;l6p5oS
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);//显示登录所对应的面板
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrm() {
		setTitle("\u767B\u9646\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 684);
		contentPane =  new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);//居中显示
		JLabel label = new JLabel("\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF\u767B\u9646\u754C\u9762");
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5934\u50CF.png")));
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u7528\u6237\u540D.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		userNameTextField = new JTextField();
		userNameTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5BC6\u7801.png")));
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		label_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/userType.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		userTypeComboBox = new JComboBox();
		//将UserType中的列表对象传入
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		userTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//登录按钮事件
				loginAct(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u767B\u5F55.png")));
		loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//清空按钮事件
				restValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(146)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
						.addComponent(userTypeComboBox, 0, 462, Short.MAX_VALUE)
						.addComponent(userNameTextField, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
					.addGap(104))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(190)
					.addComponent(loginButton)
					.addPreferredGap(ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
					.addComponent(resetButton)
					.addGap(184))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(216, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(208))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addGap(68))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(55)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(resetButton))
					.addGap(68))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginAct(ActionEvent ae) {//登录事件的完成
		// TODO Auto-generated method stub
		String userName = userNameTextField.getText().toString();//获取用户名
		String password = passwordTextField.getText().toString();//获取用户密码
		UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();
		if(StringUtil.isEmpty(userName)){//调用一个equals功能函数，在StringUtil文件下
			JOptionPane.showMessageDialog(this, "用户名不能为空！");//弹出一个窗口
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(this, "密码不能为空！");
			return;
		}
		Admin admin =null;
		if("系统管理员".equals(selectedItem.getName())){
			AdminDao adminDao = new AdminDao();//按照adminDao中的数据进行输入，然后将数据统一与数据库中的信息进行比较，
			Admin adminTmp = new Admin();
			adminTmp.setName(userName);
			adminTmp.setPassword(password);
			admin = adminDao.login(adminTmp);
			adminDao.closeDao();
			if(admin == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+admin.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, admin).setVisible(true);
		}else if("教师".equals(selectedItem.getName())){//同上
			//教师登录
			Teacher teacher = null;
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			teacherTmp.setName(userName);
			teacherTmp.setPassword(password);
			teacher = teacherDao.login(teacherTmp);
			teacherDao.closeDao();
			if(teacher == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+teacher.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, teacher).setVisible(true);
		}else{//同上
			//学生登录
			Student student = null; 
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			studentTmp.setName(userName);
			studentTmp.setPassword(password);
			student = studentDao.login(studentTmp);
			studentDao.closeDao();
			if(student == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+student.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, student).setVisible(true);
		}
	}

	protected void restValue(ActionEvent ae) {//清空事件的完成
		// TODO Auto-generated method stub
		userNameTextField.setText("");
		passwordTextField.setText("");
		userTypeComboBox.setSelectedIndex(0);
	}
}
