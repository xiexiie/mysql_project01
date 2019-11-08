package view;

/*
 * 增加学生班级信息
 */
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;

import dao.ClassDao;
import model.StudentClass;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudentClassFrm extends JInternalFrame {//继承内部类   JinternalFrame的使用可以最大化、最小化、关闭窗口、加入菜单等功能
	private JTextField classNameTextField;//班级名称框
	private JTextArea classInfotextArea;//班级信息框

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentClassFrm frame = new AddStudentClassFrm();
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
	public AddStudentClassFrm() {
		setClosable(true);//可以关闭窗口
		setIconifiable(true);//意味着可以图标化此内部窗体
		setTitle("\u6DFB\u52A0\u73ED\u7EA7\u4FE1\u606F");
		setBounds(100, 100, 727, 519);
		
		JLabel label = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		label.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label.setFont(new Font("黑体", Font.PLAIN, 20));
		
		classNameTextField = new JTextField();
		classNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u73ED\u7EA7\u4FE1\u606F\uFF1A");
		label_1.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		label_1.setFont(new Font("黑体", Font.PLAIN, 20));
		
		classInfotextArea = new JTextArea();
		
		JButton submitButton = new JButton("\u63D0\u4EA4");
		submitButton.addActionListener(new ActionListener() {//添加班级信息的确定事件
			public void actionPerformed(ActionEvent ae) {
				submitClass(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("黑体", Font.PLAIN, 20));
		
		JButton restButton = new JButton("\u91CD\u7F6E");
		restButton.addActionListener(new ActionListener() {//重置对应的事件
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		restButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		restButton.setFont(new Font("黑体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(150)
					.addComponent(submitButton)
					.addPreferredGap(ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
					.addComponent(restButton)
					.addGap(145))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(classInfotextArea, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addGap(105))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(98)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(classInfotextArea, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(restButton)
						.addComponent(submitButton))
					.addGap(81))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void submitClass(ActionEvent ae) {//添加班级信息对应的事件的实现
		// TODO Auto-generated method stub
		String className = classNameTextField.getText().toString();//获取班级名字
		String classInfo = classInfotextArea.getText().toString();//获取班级信息
		if(StringUtil.isEmpty(className)){                   //判断是否输入班级名称
			JOptionPane.showMessageDialog(this, "班级名称不能为空！");
			return;
		}
		StudentClass scl = new StudentClass();
		scl.setName(className);//
		scl.setInfo(classInfo);//将所添加班级信息传入
		ClassDao classDao = new ClassDao();    //在班级数据库中进行添加
		if(classDao.addClass(scl)){
			JOptionPane.showMessageDialog(this, "班级添加成功！");
		}else{
			JOptionPane.showMessageDialog(this, "班级添加失败！");
		}
		classDao.closeDao();
		resetValue(ae);//显示对应的面板
	}

	protected void resetValue(ActionEvent e) {//重置事件对应的函数的实现
		// TODO Auto-generated method stub
		classNameTextField.setText("");
		classInfotextArea.setText("");
	}
}
