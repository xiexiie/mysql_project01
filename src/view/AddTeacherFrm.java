package view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import dao.TeacherDao;
import model.Teacher;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AddTeacherFrm extends JInternalFrame {
	private JTextField teacherNameTextField;
	private JTextField teacherTitleTextField;
	private JTextField teacherAgeTextField;
	private JRadioButton teacherSexManRadioButton;
	private JRadioButton teacherSexFemalRadioButton;
	private JPasswordField teacherPasswordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeacherFrm frame = new AddTeacherFrm();
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
	public AddTeacherFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u6559\u5E08");
		setBounds(100, 100, 663, 481);
		
		JLabel label = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u8001\u5E08.png")));
		
		teacherNameTextField = new JTextField();
		teacherNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6559\u5E08\u6027\u522B\uFF1A");
		label_1.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u6027\u522B.png")));
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		teacherSexManRadioButton = new JRadioButton("\u7537");
		teacherSexManRadioButton.setSelected(true);
		teacherSexManRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		teacherSexFemalRadioButton = new JRadioButton("\u5973");
		teacherSexFemalRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		buttonGroup.add(teacherSexManRadioButton);
		buttonGroup.add(teacherSexFemalRadioButton);
		
		JLabel label_2 = new JLabel("\u6559\u5E08\u804C\u79F0\uFF1A");
		label_2.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u804C\u79F0\u8BC4\u5B9A.png")));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		teacherTitleTextField = new JTextField();
		teacherTitleTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6559\u5E08\u5E74\u9F84\uFF1A");
		label_3.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u5E74\u9F84.png")));
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		teacherAgeTextField = new JTextField();
		teacherAgeTextField.setColumns(10);
		
		JButton submitButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addTeacherAct(ae);//������ʦ��Ϣ�¼�
			}
		});
		submitButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("\u91CD\u7F6E\u8868\u5355");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//���ð�ť�¼�
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		JLabel label_4 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_4.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u5BC6\u7801.png")));
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		teacherPasswordField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(175, Short.MAX_VALUE)
							.addComponent(submitButton)
							.addGap(146)
							.addComponent(resetButton))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_4)
									.addGap(18)
									.addComponent(teacherPasswordField))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_3, Alignment.LEADING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_2)
												.addComponent(label)
												.addComponent(label_1))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(teacherNameTextField, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(teacherSexManRadioButton)
														.addGap(113)
														.addComponent(teacherSexFemalRadioButton))
													.addComponent(teacherTitleTextField)
													.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(teacherAgeTextField, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))))))))))
					.addGap(149))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(teacherNameTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(teacherSexManRadioButton)
						.addComponent(teacherSexFemalRadioButton))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(teacherTitleTextField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(teacherAgeTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(teacherPasswordField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton)
						.addComponent(resetButton))
					.addGap(32))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void resetValue(ActionEvent ae) {//���ð�ť�¼���ʵ��
		// TODO Auto-generated method stub
		teacherNameTextField.setText("");
		teacherTitleTextField.setText("");
		teacherAgeTextField.setText("");
		teacherSexManRadioButton.setSelected(true);
		teacherPasswordField.setText("");
	}

	protected void addTeacherAct(ActionEvent ae) {//�����ʦ�¼���ʵ��
		// TODO Auto-generated method stub
		String teacherName = teacherNameTextField.getText().toString();//��ȡ��ʦ�����Ϣ
		String teacherSex = teacherSexManRadioButton.isSelected() ? teacherSexManRadioButton.getText().toString() : teacherSexFemalRadioButton.getText().toString();
		String teacherTitle = teacherTitleTextField.getText().toString();
		String teacherPassword = teacherPasswordField.getText().toString();
		int teacherAge = 0;
		try {
			teacherAge = Integer.parseInt(teacherAgeTextField.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "����ֻ�����������֣�");
			return;
		}
		if(StringUtil.isEmpty(teacherName)){
			JOptionPane.showMessageDialog(this, "��ʦ����������д��");
			return;
		}
		if(StringUtil.isEmpty(teacherTitle)){
			JOptionPane.showMessageDialog(this, "��ʦְ�Ʊ�����д��");
			return;
		}
		if(teacherAge == 0 || teacherAge < 0){
			JOptionPane.showMessageDialog(this, "��ʦ����������0��");
			return;
		}
		if(StringUtil.isEmpty(teacherPassword)){
			JOptionPane.showMessageDialog(this, "��ʦ��¼���������д��");
			return;
		}
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		teacher.setSex(teacherSex);
		teacher.setTitle(teacherTitle);
		teacher.setAge(teacherAge);
		teacher.setPassword(teacherPassword);
		TeacherDao teacherDao = new TeacherDao();//���ݿ��н��в���
		if(teacherDao.addTeacher(teacher)){
			JOptionPane.showMessageDialog(this, "��ʦ��ӳɹ���");
		}else{
			JOptionPane.showMessageDialog(this, "��ʦ���ʧ�ܣ�");
		}
		resetValue(ae);
	}
}
