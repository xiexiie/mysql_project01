package view;

//主界面以及关于我们的实现
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserType;
import java.awt.Font;//选择用户模型

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public static UserType userType;
	public static Object userObject;
	private JMenuItem addStudentMenuItem ;
	private JMenu manageClassMenu ;
	private JMenu manageTeacherMenu;
	private JMenuItem addTeacherMenuItem;
	private JMenu courseMenu;
	private JMenuItem addClassMenuItem;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrm frame = new MainFrm(null,null);
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
	public MainFrm(UserType userType,Object userObject) {
		this.userType = userType;
		this.userObject = userObject;
		setTitle("\u5B66\u751F\u4FE1\u606F\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 774);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7CFB\u7EDF\u8BBE\u7F6E.png")));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//登录密码事件
				editPassword(ae);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u4FEE\u6539\u5BC6\u7801.png")));
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//退出事件
				if(JOptionPane.showConfirmDialog(MainFrm.this, "确定退出么？") == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9000\u51FA.png")));
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u5B66\u751F\u7BA1\u7406");
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		menuBar.add(menu_1);
		
		addStudentMenuItem = new JMenuItem("\u5B66\u751F\u6DFB\u52A0");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrm addStudentFrm = new AddStudentFrm();//增加学生事件
				addStudentFrm.setVisible(true);
				desktopPane.add(addStudentFrm);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		menu_1.add(addStudentMenuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("\u5B66\u751F\u5217\u8868");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//学生列表事件 
				ManageStudentFrm studentManageFrm = new ManageStudentFrm();
				studentManageFrm.setVisible(true);
				desktopPane.add(studentManageFrm);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7528\u6237\u5217\u8868.png")));
		menu_1.add(menuItem_3);
		
		manageClassMenu = new JMenu("\u73ED\u7EA7\u7BA1\u7406");
		manageClassMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u7BA1\u7406.png")));
		menuBar.add(manageClassMenu);
		
		addClassMenuItem = new JMenuItem("\u73ED\u7EA7\u6DFB\u52A0");
		addClassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//添加学生班级事件
				addStudentClass(ae);
			}
		});
		addClassMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		manageClassMenu.add(addClassMenuItem);
		
		JMenuItem menuItem_5 = new JMenuItem("\u73ED\u7EA7\u7BA1\u7406");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageClassFrm classManageFrm = new ManageClassFrm();//添加班级事件管理
				classManageFrm.setVisible(true);
				desktopPane.add(classManageFrm);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u5217\u8868.png")));
		manageClassMenu.add(menuItem_5);
		
		manageTeacherMenu = new JMenu("\u6559\u5E08\u7BA1\u7406");
		manageTeacherMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8001\u5E08.png")));
		menuBar.add(manageTeacherMenu);
		
		addTeacherMenuItem = new JMenuItem("\u6DFB\u52A0\u6559\u5E08");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//添加教师按钮事件
				AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
				addTeacherFrm.setVisible(true);
				desktopPane.add(addTeacherFrm);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		manageTeacherMenu.add(addTeacherMenuItem);
		
		JMenuItem menuItem_8 = new JMenuItem("\u6559\u5E08\u5217\u8868");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//教师列表事件
				ManageTeacherFrm manageTeacherFrm = new ManageTeacherFrm();
				manageTeacherFrm.setVisible(true);
				desktopPane.add(manageTeacherFrm);
			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7528\u6237\u5217\u8868.png")));
		manageTeacherMenu.add(menuItem_8);
		
		courseMenu = new JMenu("\u8BFE\u7A0B\u7BA1\u7406");
		courseMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		courseMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		menuBar.add(courseMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6DFB\u52A0\u8BFE\u7A0B");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//添加课程事件
				AddCourseFrm addCourseFrm = new AddCourseFrm();
				addCourseFrm.setVisible(true);
				desktopPane.add(addCourseFrm);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0.png")));
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		courseMenu.add(mntmNewMenuItem);
		
		JMenuItem courseListMenuItem = new JMenuItem("\u8BFE\u7A0B\u5217\u8868");
		courseListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//课程列表事件
				ManageCourseFrm manageCourseFrm = new ManageCourseFrm();
				manageCourseFrm.setVisible(true);
				desktopPane.add(manageCourseFrm);
			}
		});
		courseListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B\u5217\u8868.png")));
		courseListMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		courseMenu.add(courseListMenuItem);
		
		JMenu menu_4 = new JMenu("\u9009\u8BFE\u7BA1\u7406");
		menu_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9009\u62E9.png")));
		menu_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		menuBar.add(menu_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u9009\u8BFE\u7BA1\u7406");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//选课管理事件
				ManageSelectedCourseFrm manageSelectedCourseFrm = new ManageSelectedCourseFrm();
				manageSelectedCourseFrm.setVisible(true);
				desktopPane.add(manageSelectedCourseFrm);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		menu_4.add(mntmNewMenuItem_1);
		
		JMenu menu_3 = new JMenu("\u5E2E\u52A9");
		menu_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5E2E\u52A9.png")));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//关于我们事件
				aboutUs(ae);
			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5173\u4E8E\u6211\u4EEC.png")));
		menu_3.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 128, 128));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setAuthority();
	}

	protected void addStudentClass(ActionEvent ae) {//添加学生班级事件的实现
		// TODO Auto-generated method stub
		AddStudentClassFrm sca = new AddStudentClassFrm();
		sca.setVisible(true);//设置面板可见
		desktopPane.add(sca);
	}

	protected void editPassword(ActionEvent ae) {//登录密码
		// TODO Auto-generated method stub
		EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
		editPasswordFrm.setVisible(true);
		desktopPane.add(editPasswordFrm);
	}

	protected void aboutUs(ActionEvent ae) {//关于我们事件的实现
		// TODO Auto-generated method stub
		String info = "谢云杰制作\n";
		info += "网址：www.mengxiang.pro \n";
		info += "加油，老铁！！！";
		String[] buttons = {"迫不及待去看看!","心情不好以后再说!"};
		//用int接收传输来的对象
		int ret = JOptionPane.showOptionDialog(this, info, "关于我们", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(LoginFrm.class.getResource("/images/酷男孩.png")), buttons, null);
		if(ret == 0){
			//采用Java 调用系统浏览器打开制定
			try {
				URI uri = new URI("http://www.mengxiang.pro");
				//Desktop.getDesktop().browse(uri);//java打开网页
				Runtime.getRuntime().exec("explorer http://www.mengxiang.pro");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(this, "扎心了，老铁！！！");
		}
	}
	private void setAuthority(){//设置用户权限事件的实现
		if("学生".equals(userType.getName())){
			addStudentMenuItem.setEnabled(false);
			manageClassMenu.setEnabled(false);
			manageTeacherMenu.setEnabled(false);
            courseMenu.setEnabled(false);
		}
		if("教师".equals(userType.getName())){
			addTeacherMenuItem.setEnabled(false);
			addStudentMenuItem.setEnabled(false);
			addClassMenuItem.setEnabled(false);
		}
	}
}
