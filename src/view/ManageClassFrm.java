package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import dao.ClassDao;
import model.StudentClass;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageClassFrm extends JInternalFrame {
	private JTextField searchClassNameTextField;
	private JTable classListTable;
	private JTextField editClassNameTextField;
	private JTextArea editClassInfoTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageClassFrm frame = new ManageClassFrm();
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
	public ManageClassFrm() {
		setClosable(true);
		setIconifiable(true);//窗体的放缩和关闭
		setTitle("\u73ED\u7EA7\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 773, 562);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		label.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchClassNameTextField = new JTextField();
		searchClassNameTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentClass sc = new StudentClass();//确认查询事件
				sc.setName(searchClassNameTextField.getText().toString());
				setTable(sc);
				//信息界面的刷新
			}
		});
		searchButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		label_1.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editClassNameTextField = new JTextField();
		editClassNameTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u73ED\u7EA7\u4FE1\u606F\uFF1A");
		label_2.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editClassInfoTextArea = new JTextArea();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {//修改班级信息对应的事件
			public void actionPerformed(ActionEvent ae) {
				submitEditAct(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton submitDeleteButton = new JButton("\u5220\u9664");
		submitDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {//删除班级信息对应的事件
				deleteClassAct(ae);
			}
		});
		submitDeleteButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u5220\u9664.png")));
		submitDeleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(160)
							.addComponent(label)
							.addGap(18)
							.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(searchButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_2)
									.addGap(18)
									.addComponent(editClassInfoTextArea))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addGap(18)
									.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
							.addGap(102)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(submitDeleteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(submitEditButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchClassNameTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(editClassNameTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(editClassInfoTextArea, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(label_2)
									.addGap(32))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(submitEditButton)
							.addGap(34)
							.addComponent(submitDeleteButton)
							.addContainerGap())))
		);
		
		classListTable = new JTable();//创建的一个表格，三行两列的表格
		classListTable.addMouseListener(new MouseAdapter() {//编辑班级信息修改框内容
			@Override
			public void mouseClicked(MouseEvent me) {
				selectedTableRow(me);
			}
		});
		classListTable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		classListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"\u73ED\u7EA7\u7F16\u53F7","\u73ED\u7EA7\u540D\u79F0" , "\u73ED\u7EA7\u4FE1\u606F\u4ECB\u7ECD"//表格头部的内容
			}
		) {
			boolean[] columnEditables = new boolean[] {//表头的状态
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		classListTable.getColumnModel().getColumn(2).setPreferredWidth(316);
		scrollPane.setViewportView(classListTable);//大框架
		getContentPane().setLayout(groupLayout);//用getContentPane()方法获得JFrame的内容面板，再对其加入组件，设置布局
		setTable(new StudentClass());//将StudentClass表中的信息导入刷新列表
	}
	protected void deleteClassAct(ActionEvent ae) {//删除功能的实现
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "您确定删除么？") != JOptionPane.OK_OPTION){//弹出一个单选框
			return;
		}
		int index = classListTable.getSelectedRow();//获取操作行数
		if(index == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据!");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();//创建表单模型，获取表单数据
		int id = Integer.parseInt(dft.getValueAt(classListTable.getSelectedRow(), 0).toString());
		ClassDao classDao = new ClassDao();
		if(classDao.delete(id)){
			JOptionPane.showMessageDialog(this, "删除成功!");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败!");
		}
		classDao.closeDao();//数据库中进行删除
		setTable(new StudentClass());//更新
	}

	protected void submitEditAct(ActionEvent ae) {//修改班级信息事件的实现
		// TODO Auto-generated method stub
		ClassDao classDao = new ClassDao();
		int index = classListTable.getSelectedRow();//获取返回所在的行数
		if(index == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据!");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();//表格模型
		String className = dft.getValueAt(classListTable.getSelectedRow(), 1).toString();//显示获取即将要修改的信息
		String classInfo = dft.getValueAt(classListTable.getSelectedRow(), 2).toString();//显示获取即将要修改的信息
		String editClassName = editClassNameTextField.getText().toString();//显示获取即将要修改的信息
		String editClassInfo = editClassInfoTextArea.getText().toString();//显示获取即将要修改的信息
		if(StringUtil.isEmpty(editClassName)){
			JOptionPane.showMessageDialog(this, "请填写要修改的名称!");
			return;
		}
		if(className.equals(editClassName) && classInfo.equals(editClassInfo)){
			JOptionPane.showMessageDialog(this, "您还没有做任何修改!");
			return;
		}
		int id = Integer.parseInt(dft.getValueAt(classListTable.getSelectedRow(), 0).toString());
		StudentClass sc = new StudentClass();//将修改后的信息传入
		sc.setId(id);
		sc.setName(editClassName);
		sc.setInfo(editClassInfo);
		if(classDao.update(sc)){
			JOptionPane.showMessageDialog(this, "更新成功!");
		}else{
			JOptionPane.showMessageDialog(this, "更新失败!");
		}
		classDao.closeDao();
		setTable(new StudentClass());
	}

	protected void selectedTableRow(MouseEvent me) {//编辑班级信息修改框内容的实现
		// TODO Auto-generated method stub
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();
		editClassNameTextField.setText(dft.getValueAt(classListTable.getSelectedRow(), 1).toString());//获取编辑班级名称框内的显示
		editClassInfoTextArea.setText(dft.getValueAt(classListTable.getSelectedRow(), 2).toString());//获取编辑班级信息框内的显示
	}

	private void setTable(StudentClass studentClass){//获取学生班级信息，确认事件的实现
		DefaultTableModel dft = (DefaultTableModel) classListTable.getModel();
		dft.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(studentClass);
		for (StudentClass sc : classList) {
			Vector v = new Vector();//集合
			v.add(sc.getId());
			v.add(sc.getName());
			v.add(sc.getInfo());
			dft.addRow(v);//循环下去。模型
		}
		classDao.closeDao();
	}
}
