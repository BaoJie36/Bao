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

public class SalesPanel extends JPanel implements ActionListener {
	
	private JScrollPane scrollPanel;
	private JButton addButton, delButton, searchButton, outputButton,checkButton;
	private static String sano, date, eno, sum, cno, account, note;
//	 { "序号", "销售单号", "日期", "员工编号", "总金额", "客户编号", "记账人" ,"备注"};
	private static MyTable table;
	private static DefaultTableModel model = null;
	private JTextField cxText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;

	SalesPanel() {
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
	

	private void setOutput() {
		try {
			SQL.SalesQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setIcon() {
		// TODO Auto-generated method stub
		searchIcon = new ImageIcon("search.png");
		searchLabel = new JLabel(searchIcon);
		searchLabel.setBounds(100, 28, 32, 32);
		this.add(searchLabel);
	}


	private void setListener() {
		SQL.setSalesmodel(model);
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		outputButton.addActionListener(this);
		checkButton.addActionListener(this);

		

		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if(e.getButton() == MouseEvent.BUTTON1) {
					getSalesMess();
//					SalesAddMess.setSales(sano, date, eno, sum, cno, account, note);
					CheckSalesMess.setSales(sano, date, eno, sum, cno, account, note);

				}
			}
		});
		 

		
	}

	private void setTable() {
		String[][] datas = {};
		String[] titles = { "序号", "销售单号", "日期", "员工编号",  "客户编号", "记账人" ,"总金额","备注"};
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
		cxText = new JTextField(18);
		cxText.setBounds(130, 30, 120, 25);
		add(cxText);


JLabel employeeLabel = new JLabel("您好！"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);

	}

	private void setButton() {

		searchButton = new JButton("搜索");
		addButton = new JButton("添加");
		delButton = new JButton("删除");
		outputButton = new JButton("显示所有信息");
		checkButton = new JButton("查看");

		Font font = new Font("微软雅黑", Font.PLAIN, 12);
		searchButton.setFont(font);
		addButton.setFont(font);
		delButton.setFont(font);
		outputButton.setFont(font);
		checkButton.setFont(font);

		searchButton.setBounds(260, 30, 60, 25);
		addButton.setBounds(500, 510, 60, 30);
		checkButton.setBounds(575, 510, 60, 30);
		delButton.setBounds(650, 510, 60, 30);
		outputButton.setBounds(725, 510, 120, 30);

		add(searchButton);
		add(checkButton);
		add(addButton);
		add(delButton);
		add(outputButton);

	}


	static void getSalesMess() {
//		sano, date, eno, sum, cno, account, note
//		{ "序号", "销售单号", "日期", "员工编号", "总金额", "客户编号", "记账人" ,"备注"};
		int Sales_row = table.getSelectedRow();
		sano = (String) table.getValueAt(Sales_row, model.findColumn("销售单号"));// 返回值为 row和 column的单元格值。
		eno = (String) table.getValueAt(Sales_row, model.findColumn("员工编号"));
		date = (String) table.getValueAt(Sales_row, model.findColumn("日期"));
		sum = (String) table.getValueAt(Sales_row, model.findColumn("总金额"));
		cno = (String) table.getValueAt(Sales_row, model.findColumn("客户编号"));
		account = (String) table.getValueAt(Sales_row, model.findColumn("记账人"));
		note = (String) table.getValueAt(Sales_row, model.findColumn("备注"));
//		
	}
	
	
	public static MyTable GetTable() {
		return table;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			new SalesAddMess();
			SalesAddMess.setSales(null, null, null, "0", null, null, null);
		}
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "该操作无法恢复！是否继续？", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.SalesDelete(sano);
					SQL.SalesQuery();
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == searchButton) {
			
			
			String conditions = cxText.getText();
			
			try {
				SQL.SalesSel(conditions);//按条件搜索药品信息

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.SalesQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == checkButton) {
//			new SalesAddMess();
			new CheckSalesMess();
			try {
				SQL.SalesDetailsQuery(sano);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CheckSalesMess.GetTable();
		}
	}
}
