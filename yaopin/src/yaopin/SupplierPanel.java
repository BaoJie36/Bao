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




public class SupplierPanel extends JPanel implements ActionListener {
//1.定义各种组件变量名
	
	//下拉框:选择搜索条件
	private JComboBox suppliersBox;
	//滚动条:表格需要查看更多信息
	private JScrollPane scrollPanel;
	//按钮：添加、删除、查询、显示所有药品
	private JButton addButton, delButton, searchButton, outputButton,updateButton; 
	//供应商信息字段变量名:
	private static String sno, name, address, tell, cardno, bank;
	//表格:显示查询药品信息结果
	private static MyTable table;
	private static DefaultTableModel model = null;
	//搜索条件文本框
	private JTextField cxText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;
		
//2.供应商管理模块面板窗体构的造方法
	public SupplierPanel() {
		// TODO Auto-generated constructor stub
		init();
		setLayout(null);
		Color c = new Color(153,204,204);
		this.setBackground(c);
		}
		
//3.创建组件的方法
	private void init() {
		// TODO Auto-generated method stub
		setIcon();
		setButton();
		setText();
		setTable();
		setListener();
		setOutput();
		
	}
	
private void setText() {
		cxText = new JTextField(18);
		cxText.setBounds(130, 30, 120, 25);
		add(cxText);


JLabel employeeLabel = new JLabel("您好！"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);
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
		SQL.SupplierQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


	
//5.创建按钮的方法
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
	
//7.创建显示查询结果的表格的方法
	private void setTable() {
		// TODO Auto-generated method stub
		String[][] datas = {};
		String[] titles = { "序号","供应商编号", "供应商名称", "地址", "电话", "银行账号", "开户行"};
		model = new DefaultTableModel(datas, titles);	//表模型
		table = new MyTable(model);
		this.setBackground(Color.white);				//背景颜色
		scrollPanel = new JScrollPane(table);			//垂直滚动条
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); 	//水平滚动条
		table.setRowSorter(sorter);

		TableColumn column = null;						//表的每一列
		
		//表的每一列标题字段长度
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 4)
				column.setPreferredWidth(80);
			else
				column.setPreferredWidth(110);
		}
	}
	
//8.获取表格的方法
	public static MyTable GetTable() {
		return table;
		
	}
	
//9.创建监听按钮的事件的方法
	private void setListener() {
		// TODO Auto-generated method stub
		SQL.setsuppliermodel(model);
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		outputButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		
		//双击两次鼠标，自动获取信息，修改并可修改弹窗内容
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new SuppliersUpdateMess();
//					
				}else if(e.getButton() == MouseEvent.BUTTON1) {
					getSupplierMess();
					SuppliersUpdateMess.setSupplier(sno, name, address, tell, cardno, bank);
				}
			}
		});
		
		//左键单击当前面板内表格外的地方取消表格选中及书籍数据清空
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							table.clearSelection();// 清空表格选择
							SuppliersUpdateMess.setSupplier(null, null, null, null, null, null); // 书籍数据清空
						}
					}
				});
		
	}
		
//String sno, name, address, tell, cardno, bank;
//10.获取供应商信息
	static void getSupplierMess() {

		int Suppliers_row = table.getSelectedRow();
		sno = (String) table.getValueAt(Suppliers_row, model.findColumn("供应商编号"));// 返回值为 row和 column的单元格值。
		name = (String) table.getValueAt(Suppliers_row, model.findColumn("供应商名称"));
		address = (String) table.getValueAt(Suppliers_row, model.findColumn("地址"));
		tell = (String) table.getValueAt(Suppliers_row, model.findColumn("电话"));
		cardno = (String) table.getValueAt(Suppliers_row, model.findColumn("银行账号"));
		bank = (String) table.getValueAt(Suppliers_row, model.findColumn("开户行"));
		

	}
	
//11.按钮事件的触发方法
	public void actionPerformed(ActionEvent e) {
		//表模型的建立
		SQL.setsuppliermodel(model);
		//(1)"添加"按钮触发:添加供应商信息窗口
		if(e.getSource() == addButton) {
			new SuppliersAddMess();	
		}
		//(2)"删除"按钮触发:删除供应商信息
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "删除后无法恢复！是否继续？", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.SupplierDelete(sno);
					SQL.SupplierQuery();	//删除后通过数据库查询所有供应商信息,相当于刷新一下
				} catch (Exception e1) {	//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//(3)"查询"按钮触发:查询供应商信息
		else if(e.getSource() == searchButton) {
			
			
			String conditions = cxText.getText();
			
			try {
				SQL.SupplierSel(conditions);//按条件搜索药品信息

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if (e.getSource() == updateButton) {
			new SuppliersUpdateMess();
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.SupplierQuery();	//执行SQL语句
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
}