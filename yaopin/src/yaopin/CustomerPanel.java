package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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

import yaopin.MyTable;
import yaopin.SQL;
import yaopin.CustomerAddMess;
import yaopin.CustomerUpdateMess;

public class CustomerPanel extends JPanel implements ActionListener{
	private JTextField jt;
	private JComboBox box2;
	private static DefaultTableModel model=null;
	private JScrollPane scroll;
	private static MyTable table;
	private JButton AddButton,DelButton,SearchButton,OutputButton,UpdateButton;
	private static String cno,name,address,tell;
	private JLabel biaoti;
	public CustomerPanel() {
		init();
		setLayout(null); 
	}
	void init() {
		setcheck();
		setButton();
		checkTable();
		setListener();
		setOutput();
		this.setBackground(Color.white);
		
	}
	 void setOutput() {
		
		
	}

	void setcheck() {
		biaoti=new JLabel("客户信息管理");
		biaoti.setFont(new Font("宋体 ", Font.BOLD, 22));
		biaoti.setBounds(50, 0, 200, 95);
		add(biaoti);
		
	
		String check[]= {"查询条件","编号","姓名","地址","电话"};
		box2=new JComboBox(check);
		box2.setBounds(400,30,110,25);
		jt = new JTextField();
		jt.setBounds(520, 30, 120, 25);
		
		SearchButton = new JButton("搜索");
		SearchButton.setBounds(650, 30, 60, 25);
        add(box2);
		add(jt);
		add(SearchButton);
	}
	void setButton() {
		AddButton = new JButton("添加");
		DelButton = new JButton("删除");
		OutputButton = new JButton("显示");
		UpdateButton = new JButton("修改");

		AddButton.setBounds(445, 530, 90, 30);
		DelButton.setBounds(555, 530, 90, 30);
		OutputButton.setBounds(665, 530, 90, 30);
		UpdateButton.setBounds(765, 530, 90, 30);
		
		add(AddButton);
		add(DelButton);
		add(OutputButton);
		add(UpdateButton);
	}
	void checkTable() {
		String[][] datas = {};
		String[] titles = { "序号", "客户编号", "客户姓名", "地址", "联系电话" };

		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scroll = new JScrollPane(table);
		scroll.setBounds(80, 60, 930, 445);
		add(scroll);

		RowSorter sorter = new TableRowSorter(model); // 水平滚动条
		table.setRowSorter(sorter);

		// 设置表格宽度
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 5)
				column.setPreferredWidth(90);
			else if (i == 2)
				column.setPreferredWidth(110);
			else if (i == 7)
				column.setPreferredWidth(30);
			else
				column.setPreferredWidth(50);
		}
		this.setBackground(Color.white);
		SQL.setTableCenter(table, table.getColumnCount());
	}
	public static MyTable GetTable() {
	
		return table;
	}
	void setListener() {
		SQL.setcustomermodel(model);

		AddButton.addActionListener(this);
		DelButton.addActionListener(this);
		UpdateButton.addActionListener(this);
		SearchButton.addActionListener(this);
		OutputButton.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new CustomerUpdateMess();
//					new BookDetail_OnlyRead();
				} else if (e.getButton() == MouseEvent.BUTTON1) {
					getCustomerMess();
					CustomerUpdateMess.setCustomer(cno, name, address, tell);
				}
			}
		});

		// 左键单击当前面板内表格外的地方取消表格选中及客户数据清空
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();// 清空表格选择
					CustomerAddMess.setCustomer(null, null, null, null); // 客户数据清空
				}
			}
		});

	}
	static void getCustomerMess() {

		int customer_row = table.getSelectedRow();
		cno = (String) table.getValueAt(customer_row, model.findColumn("客户编号"));// 返回值为 row和 column的单元格值。
		name = (String) table.getValueAt(customer_row, model.findColumn("客户姓名"));
		address = (String) table.getValueAt(customer_row, model.findColumn("地址"));
		tell = (String) table.getValueAt(customer_row, model.findColumn("联系电话"));

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddButton) {
			new CustomerAddMess();
		} else if (e.getSource() == UpdateButton) {
			new CustomerUpdateMess();
		} else if (e.getSource() == DelButton) {
			int result = JOptionPane.showConfirmDialog(null, "该操作无法恢复！是否继续？", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.CustomerDelete(cno);
					SQL.CustomerQuery();// 删除后查询所有客户信息
				} catch (Exception e1) {// SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == SearchButton) {
			
			
			String conditions = jt.getText();
			
			try {
				SQL.CustomerSel(conditions);//按条件搜索药品信息

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if (e.getSource() == OutputButton) {

			try {
				SQL.CustomerQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
		
	}


