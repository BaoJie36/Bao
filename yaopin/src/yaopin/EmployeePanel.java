package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class EmployeePanel extends JPanel implements ActionListener {
	private JComboBox employeeBox;
	private JScrollPane scrollPanel;
	private JButton addButton, delButton, searchButton, outputButton,updateButton;
	private static String eno, name, sex, age, xueli, zhiwu, address;
	private static MyTable table;
	private static DefaultTableModel model = null;
	private JTextField selText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;


	EmployeePanel() {
		init();
		setLayout(null);
		Color c = new Color(153,204,204);
		this.setBackground(c);
	}

	void init() {
		setIcon();
		setButton();
		setText();
		setTable();
		setListener();
		setOutput();
		this.setBackground(Color.white);
	}

	private void setIcon() {
		// TODO Auto-generated method stub
		searchIcon = new ImageIcon("search.png");
		searchLabel = new JLabel(searchIcon);
		searchLabel.setBounds(100, 28, 32, 32);
		this.add(searchLabel);
	}


	private void setOutput() {
		// TODO Auto-generated method stub
		try {
			SQL.EmployeeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setListener() {
		SQL.setemployeemodel(model);
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		outputButton.addActionListener(this);
		updateButton.addActionListener(this);

		

		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new EmployeeUpdateMess();
				}else if(e.getButton() == MouseEvent.BUTTON1) {
					getEmployeeMess();
					EmployeeUpdateMess.setEmployee(eno, name, sex, age, xueli, zhiwu, address);
				}
			}
		});
		 
		//左键单击当前面板内表格外的地方取消表格选中及员工数据清空
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();// 清空表格选择
					EmployeeUpdateMess.setEmployee(null, null, null, null, null, null, null); // 员工数据清空
				}
			}
		});
		
	}

	private void setTable() {
		String[][] datas = {};
		String[] titles = { "序号", "员工编号", "员工姓名", "性别", "年龄", "学历", "职务", "住址" };
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // 水平滚动条
		table.setRowSorter(sorter);

		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 5)
				column.setPreferredWidth(90);
			else if (i == 2)
				column.setPreferredWidth(110);
			else if (i == 10)
				column.setPreferredWidth(30);
			else
				column.setPreferredWidth(50);
		}
		this.setBackground(Color.white);
		SQL.setTableCenter(table, table.getColumnCount());

	}

	private void setText() {
		selText = new JTextField(18);
		selText.setBounds(130, 30, 120, 25);
		add(selText);


JLabel employeeLabel = new JLabel("您好！"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);
	}

	private void setButton() {

		searchButton = new JButton("搜索");
		addButton = new JButton("添加");
		delButton = new JButton("删除");
		outputButton = new JButton("显示所有信息");
		updateButton = new JButton("修改");

		Font font = new Font("微软雅黑", Font.PLAIN, 12);
		searchButton.setFont(font);
		addButton.setFont(font);
		delButton.setFont(font);
		outputButton.setFont(font);
		updateButton.setFont(font);

		searchButton.setBounds(260, 30, 60, 25);
		addButton.setBounds(500, 510, 60, 30);
		updateButton.setBounds(575, 510, 60, 30);
		delButton.setBounds(650, 510, 60, 30);
		outputButton.setBounds(725, 510, 120, 30);

		add(searchButton);
		add(updateButton);
		add(addButton);
		add(delButton);
		add(outputButton);

	}


	static void getEmployeeMess() {

		int Employee_row = table.getSelectedRow();
		eno = (String) table.getValueAt(Employee_row, model.findColumn("员工编号"));// 返回值为 row和 column的单元格值。
		name = (String) table.getValueAt(Employee_row, model.findColumn("员工姓名"));
		sex = (String) table.getValueAt(Employee_row, model.findColumn("性别"));
		age = (String) table.getValueAt(Employee_row, model.findColumn("年龄"));
		xueli = (String) table.getValueAt(Employee_row, model.findColumn("学历"));
		zhiwu = (String) table.getValueAt(Employee_row, model.findColumn("职务"));
		address = (String) table.getValueAt(Employee_row, model.findColumn("住址"));
		

	}
	
	
	public static MyTable GetTable() {
		return table;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		SQL.setemployeemodel(model);
		if(e.getSource() == addButton) {
			new EmployeeAddMess();	
		}else if (e.getSource() == updateButton) {
			new EmployeeUpdateMess();
		}
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "该操作无法恢复！是否继续？", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.EmployeeDelete(eno);
					SQL.EmployeeQuery();//删除后查询所有员工信息
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == searchButton) {
			
			
			String conditions = selText.getText();
			
			try {
				SQL.EmployeeSel(conditions);//按条件搜索药品信息

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.EmployeeQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
