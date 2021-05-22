package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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








public class MedicinePanel extends JPanel implements ActionListener {
	private JComboBox medicineBox;
	private JScrollPane scrollPanel;
	private JButton addButton, delButton, searchButton, outputButton,updateButton;
	private static String mno,name,type,specifications,validity,batchno,amount,jhPrice,lsPrice,note;
	private static  MyTable table;
	private static DefaultTableModel model = null;
	private JTextField SelText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;
	

	MedicinePanel() {
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
		try {
			SQL.MedicineQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setTable() {
		String[][] datas = {};
		String[] titles = { "序号","药品编号", "类型编号","药品名称",  "规格", "有效期", "批号", "进货价", "零售价", "库存量", "备注" };
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // 水平滚动条
		table.setRowSorter(sorter);

		TableColumn column = null;


		
		table.getColumn("序号").setPreferredWidth(30);
		table.getColumn("药品编号").setPreferredWidth(60);
		table.getColumn("类型编号").setPreferredWidth(60);
		table.getColumn("药品名称").setPreferredWidth(110);
		table.getColumn("规格").setPreferredWidth(60);
		table.getColumn("有效期").setPreferredWidth(60);
		table.getColumn("批号").setPreferredWidth(140);
		table.getColumn("进货价").setPreferredWidth(60);
		table.getColumn("零售价").setPreferredWidth(60);
		table.getColumn("库存量").setPreferredWidth(60);
		 this.setBackground(Color.white);

		
		SQL.setTableCenter(table, table.getColumnCount());

	}
	
	


	public static MyTable GetTable() {
		return table;
	}
	
	
	private void setText() {
		SelText = new JTextField(18);
		SelText.setBounds(130, 30, 120, 25);
		add(SelText);


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


	void setListener() {
		SQL.setmedicinemodel(model);
		
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		updateButton.addActionListener(this);
		outputButton.addActionListener(this);
	
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new MedicineUpdateMess();
				}else if(e.getButton() == MouseEvent.BUTTON1) {
					getMedicineMess();
					MedicineUpdateMess.setMedicine(mno,name,type,specifications,validity,batchno,amount,jhPrice,lsPrice,note);
				}
			}
		});
		 
		//左键单击当前面板内表格外的地方取消表格选中及药品数据清空
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();// 清空表格选择
					MedicineUpdateMess.setMedicine(null, null, null, null, null, null, null,null,null,null); // 药品数据清空
				}
			}
		});
		
	}
	



	

	static void getMedicineMess() {

		int Medicine_row = table.getSelectedRow();
		mno = (String) table.getValueAt(Medicine_row, model.findColumn("药品编号"));// 返回值为 row和 column的单元格值。
		name = (String) table.getValueAt(Medicine_row, model.findColumn("药品名称"));
		type = (String) table.getValueAt(Medicine_row, model.findColumn("类型编号"));
		specifications = (String) table.getValueAt(Medicine_row, model.findColumn("规格"));
		batchno = (String) table.getValueAt(Medicine_row, model.findColumn("批号"));
		validity = (String) table.getValueAt(Medicine_row, model.findColumn("有效期"));
		jhPrice = (String) table.getValueAt(Medicine_row, model.findColumn("进货价"));
		lsPrice = (String) table.getValueAt(Medicine_row, model.findColumn("零售价"));
		amount = (String) table.getValueAt(Medicine_row, model.findColumn("库存量"));
		note = (String) table.getValueAt(Medicine_row, model.findColumn("备注"));
		

	}
	


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			new MedicineAddMess();
			
		}else if(e.getSource() == updateButton) {
			new MedicineUpdateMess();
		}
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "该操作无法恢复！是否继续？", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.MedicineDelete(mno);
					SQL.MedicineQuery();//删除后查询所有药品信息
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == searchButton) {
			
			
			String conditions = SelText.getText();
			
			try {
				SQL.MedicineSel(conditions);//按条件搜索药品信息

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.MedicineQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
