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

public class JinhuoPanel extends JPanel implements ActionListener {
	
	private JScrollPane scrollPanel;
	private JButton addButton, delButton, searchButton, outputButton,checkButton;
	private static String pno, date, eno, sno, account, sum, note;
	private static MyTable table;
	private static DefaultTableModel model = null;
	private JTextField cxText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;

	JinhuoPanel() {
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
			SQL.PurchaseQuery();
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
		SQL.setPurchasemodel(model);
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		outputButton.addActionListener(this);
		checkButton.addActionListener(this);

		

		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if(e.getButton() == MouseEvent.BUTTON1) {
					getJinhuoMess();
					JinhuoAddMess.setPurchase(pno, date, eno, sum, sno, account, note);
				}
			}
		});
		 

		
	}

	private void setTable() {
		String[][] datas = {};
		
		String[] titles = { "���", "��������", "��������", "Ա�����", "�ܽ��","��Ӧ�̱��", "������" ,  "��ע"};
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // ˮƽ������
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


JLabel employeeLabel = new JLabel("���ã�"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);

	}

	private void setButton() {

		searchButton = new JButton("����");
		addButton = new JButton("���");
		delButton = new JButton("ɾ��");
		outputButton = new JButton("��ʾ������Ϣ");
		checkButton = new JButton("�鿴");

		Font font = new Font("΢���ź�", Font.PLAIN, 12);
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


	static void getJinhuoMess() {
//		private static String pno, date, eno, sum, sno, sign, account, note;
		int Purchase_row = table.getSelectedRow();
		pno = (String) table.getValueAt(Purchase_row, model.findColumn("��������"));// ����ֵΪ row�� column�ĵ�Ԫ��ֵ��
		date = (String) table.getValueAt(Purchase_row, model.findColumn("��������"));
		eno = (String) table.getValueAt(Purchase_row, model.findColumn("Ա�����"));
		sum = (String) table.getValueAt(Purchase_row, model.findColumn("�ܽ��"));
		sno = (String) table.getValueAt(Purchase_row, model.findColumn("��Ӧ�̱��"));
		account = (String) table.getValueAt(Purchase_row, model.findColumn("������"));
		note = (String) table.getValueAt(Purchase_row, model.findColumn("��ע"));
//		{ "���", "��������", "��������", "Ա�����", "�ܽ��", "��Ӧ�̱��", "������" ,"��ע"};
	}
	
	
	public static MyTable GetTable() {
		return table;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			new JinhuoAddMess();
			//JinhuoAddMess.setPurchase(null, null, null, "0", null, null, null);
		}
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "�ò����޷��ָ����Ƿ������", "ɾ������", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.PurchaseDelete(pno);
					SQL.PurchaseQuery();//ɾ�����ѯ����ͼ����Ϣ
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == searchButton) {
			
			
			String conditions = cxText.getText();
			
			try {
				SQL.PurchaseSel(conditions);//����������ҩƷ��Ϣ

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.PurchaseQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == checkButton) {
			new JinhuoAddMess();
//			new CheckJinhuoMess();
			try {
				SQL.PurchaseDetailsQuery(pno);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JinhuoAddMess.GetTable();
//			JinhuoAddMess.checkPurchaseDetailsDelete();
		}
	}
}
